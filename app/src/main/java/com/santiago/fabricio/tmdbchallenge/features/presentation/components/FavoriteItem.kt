package com.santiago.fabricio.tmdbchallenge.features.presentation.components

import android.os.Build
import android.view.Gravity
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import com.santiago.fabricio.tmdbchallenge.R
import com.santiago.fabricio.tmdbchallenge.core.components.AsyncAvatarImage
import com.santiago.fabricio.tmdbchallenge.core.data.local.entity.Favorite
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.FavoritesViewModel
import com.santiago.fabricio.tmdbchallenge.theme.yellow


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoriteItem(
    favorite: Favorite,
    favoritesViewModel: FavoritesViewModel
) {
    val context = LocalContext.current
    val toast: Toast = makeText(context, R.string.remove_favorite_text, Toast.LENGTH_LONG)

    OutlinedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clearAndSetSemantics {
                contentDescription =
                    context.getString(R.string.favorites_item_description_outlined_card)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncAvatarImage(
                dataUrl = favorite.image,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.favorites_item_description_image)
                    }
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = favorite.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.favorites_item_description_title)
                    })

                Spacer(modifier = Modifier.size(16.dp))

                Button(onClick = {
                    favoritesViewModel.delete(
                        Favorite(
                            title = favorite.title,
                            image = favorite.image,
                            rating = favorite.rating,
                            releaseDate = favorite.releaseDate
                        )
                    )
                    favoritesViewModel.fetchAllFavorites()
                    toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 200)
                    toast.show()
                }, colors = ButtonDefaults.buttonColors(yellow)) {
                    Text(
                        text = stringResource(R.string.remove_favorite_button_text),
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.clearAndSetSemantics {
                            contentDescription =
                                context.getString(R.string.movies_favorite_button)}
                    )
                }
            }
        }
    }
}