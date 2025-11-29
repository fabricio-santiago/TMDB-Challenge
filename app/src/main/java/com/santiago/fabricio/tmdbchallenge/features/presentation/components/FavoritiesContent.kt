package com.santiago.fabricio.tmdbchallenge.features.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.paging.compose.LazyPagingItems
import com.santiago.fabricio.tmdbchallenge.core.data.local.entity.Favorite

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoritiesContent(
    modifier: Modifier = Modifier,
    favorities: List<Favorite>,
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "LazyColumn",
            modifier = Modifier.padding(32.dp),
            style = TextStyle(
                color = Color.Black,
                fontSize = TextUnit(value = 24f, type = TextUnitType.Sp)
            ),
            fontWeight = FontWeight.ExtraBold
        )

        // lazy column for displaying listview.
        LazyColumn {
            // populating items for listview.
            itemsIndexed(favorities) {_, language ->
                Text(language.title, modifier = Modifier.padding(15.dp))
                HorizontalDivider()
            }
        }
    }
}