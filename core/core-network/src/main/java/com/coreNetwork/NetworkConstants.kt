package com.coreNetwork

object NetworkConstants {
    const val BASE_URL = "https://api.github.com/"

    object Search{
        const val END_POINT = "search/repositories"
        const val STARS = "stars"
        const val Q = "q"
        const val SORT = "sort"
    }
    object NetworkErrors{
        const val NETWORK_ERROR = "Network Error"
        const val UNKNOWN_ERROR = "Unknown Error"
        const val USER_NOT_AUTHENTICATED = "User is not authenticated"
        const val  RESPONSE_BODY_NULL = "Response body is null"
        const val  ERROR_BODY_PARSING = "Error Body Parsing Error"
        const val CLIENT_ERROR = "Client Error"
        const val  UNKNOWN_HTTP_STATUS_CODE = "Unknown HTTP Status Code"

    }
}