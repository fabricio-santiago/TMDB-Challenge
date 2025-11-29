package com.santiago.fabricio.tmdbchallenge.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.santiago.fabricio.tmdbchallenge.R
import com.santiago.fabricio.tmdbchallenge.theme.yellow

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String,
    retry: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Button(onClick = retry, colors = ButtonDefaults.buttonColors(yellow)) {
            Text(
                text = stringResource(R.string.error_view_button_text),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(message = "Tente novamente.", retry = {})
}