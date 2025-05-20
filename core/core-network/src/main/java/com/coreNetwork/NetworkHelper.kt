package com.coreNetwork

import android.security.keystore.UserNotAuthenticatedException
import com.coreNetwork.NetworkConstants.NetworkErrors.CLIENT_ERROR
import com.coreNetwork.NetworkConstants.NetworkErrors.ERROR_BODY_PARSING
import com.coreNetwork.NetworkConstants.NetworkErrors.NETWORK_ERROR
import com.coreNetwork.NetworkConstants.NetworkErrors.RESPONSE_BODY_NULL
import com.coreNetwork.NetworkConstants.NetworkErrors.UNKNOWN_ERROR
import com.coreNetwork.NetworkConstants.NetworkErrors.UNKNOWN_HTTP_STATUS_CODE
import com.coreNetwork.NetworkConstants.NetworkErrors.USER_NOT_AUTHENTICATED
import retrofit2.Response
import java.io.IOException

object NetworkHelper {
    suspend inline fun <reified T> processCall(networkCall: () -> Response<T>): T? {
        val response = try {
            networkCall.invoke()
        } catch (throwable: IOException) {
            throw IOException("$NETWORK_ERROR ${throwable.message}")
        } catch (throwable: Throwable) {
            throw Exception("$UNKNOWN_ERROR ${throwable.message}")
        }

        return handleResponse(response)
    }

    suspend inline fun <reified T> handleResponse(response: Response<T>): T {
        return when {
            response.isSuccessful -> {
                response.body() ?: throw Exception(RESPONSE_BODY_NULL)
            }

            response.code() == 401 -> throw UserNotAuthenticatedException(USER_NOT_AUTHENTICATED)
            response.code() in 400..500 -> {
                val errorBody = try {
                    response.errorBody()?.string() ?: UNKNOWN_ERROR
                } catch (throwable: Throwable) {
                    throw Exception("$ERROR_BODY_PARSING ${throwable.message}")
                }
                throw Exception("$CLIENT_ERROR $errorBody")
            }

            else -> throw Exception("$UNKNOWN_HTTP_STATUS_CODE ${response.code()}")
        }
    }
}