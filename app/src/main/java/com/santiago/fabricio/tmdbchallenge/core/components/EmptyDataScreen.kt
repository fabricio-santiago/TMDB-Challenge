package com.santiago.fabricio.tmdbchallenge.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.santiago.fabricio.tmdbchallenge.R

@Composable
fun EmptyDataScreen() {
    Column(modifier = Modifier
        .padding(120.dp)
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)
        .clip(shape = RoundedCornerShape(16.dp)),
    ) {
        Box(modifier = Modifier
            .size(240.dp)
        ) {
            Text(
                text = stringResource(R.string.empty_data_screen_description),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}