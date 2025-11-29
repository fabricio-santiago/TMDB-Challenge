package com.santiago.fabricio.tmdbchallenge.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.santiago.fabricio.tmdbchallenge.ui.theme.black
import com.santiago.fabricio.tmdbchallenge.ui.theme.yellow

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    retry: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background,
            )
    ) {
        ErrorView(
            message = message,
            retry = retry,
            modifier = modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(message = "Tente novamente.", retry = {})
}