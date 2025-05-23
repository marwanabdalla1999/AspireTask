package com.core_ui.commonUi

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.R

@Composable
fun SearchView(
    query: String,
    modifier: Modifier = Modifier,
    onQueryChanged: (String) -> Unit,
    onQuerySubmitted: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(value = query,
        onValueChange = {
            onQueryChanged(it)
        },
        placeholder = { Text(stringResource(R.string.search)) },
        singleLine = true,
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            onQuerySubmitted(query)
            keyboardController?.hide()
        }),
        trailingIcon = {
            IconButton(onClick = {
                onQuerySubmitted(query)
            }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        })
}

@Preview
@Composable
fun SearchViewPreview() {
    SearchView(query = "", onQueryChanged = {}, onQuerySubmitted = {})

}