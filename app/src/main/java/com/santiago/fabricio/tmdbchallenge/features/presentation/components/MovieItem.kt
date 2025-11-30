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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.santiago.fabricio.tmdbchallenge.core.data.remote.model.Movie
import com.santiago.fabricio.tmdbchallenge.core.util.Constants.BASE_URL_IMAGE
import com.santiago.fabricio.tmdbchallenge.core.util.UtilFunctions.convertDate
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.FavoritesViewModel
import com.santiago.fabricio.tmdbchallenge.theme.yellow
import java.text.DecimalFormat
import kotlin.text.contains

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MovieItem(
    movie: Movie,
    favoritesViewModel: FavoritesViewModel,
    favorites: List<Favorite>
) {
    val context = LocalContext.current
    val decimalFormat = DecimalFormat("#.##")
    val toastAdd: Toast = makeText(context, R.string.add_favorite_text, Toast.LENGTH_LONG)
    val toastRemove: Toast = makeText(context, R.string.remove_favorite_text, Toast.LENGTH_LONG)

    OutlinedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clearAndSetSemantics {
                contentDescription =
                    context.getString(R.string.movies_item_description_outlined_card)
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
                dataUrl = BASE_URL_IMAGE + movie.posterPath,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.movies_item_description_image)
                    }
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.movies_item_description_title)
                    })

                Spacer(modifier = Modifier.size(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = String.format("Avaliação média: %s", decimalFormat.format(movie.voteAverage)),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.clearAndSetSemantics {
                            contentDescription =
                                context.getString(R.string.movies_item_description_rating)
                        })
                    Icon(imageVector = Icons.Filled.Star,
                        contentDescription = "star",
                        modifier = Modifier
                            .size(24.dp)
                            .clearAndSetSemantics {
                                contentDescription =
                                    context.getString(R.string.movies_item_description_rating_star)
                            },
                        tint = Color.Yellow
                    )
                }

                Spacer(modifier = Modifier.size(8.dp))

                Text(text = String.format("Data de lançamento: %s", convertDate(movie.releaseDate, "dd/MM/yyyy")),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clearAndSetSemantics {
                        contentDescription =
                            context.getString(R.string.movies_item_description_release_date)
                })

                Spacer(modifier = Modifier.size(8.dp))

                Button(onClick = {
                    if(inFavoriteList(favorites, movie.title)) {
                        favoritesViewModel.delete(
                            Favorite(
                                title = movie.title,
                                image = BASE_URL_IMAGE + movie.posterPath,
                                rating = movie.voteAverage,
                                releaseDate = movie.releaseDate
                            )
                        )
                        toastRemove.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 200)
                        toastRemove.show()

                    }else{
                        favoritesViewModel.insert(
                            Favorite(
                                title = movie.title,
                                image = BASE_URL_IMAGE + movie.posterPath,
                                rating = movie.voteAverage,
                                releaseDate = movie.releaseDate
                            )
                        )
                        toastAdd.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 200)
                        toastAdd.show()
                    }
                }, colors = ButtonDefaults.buttonColors(yellow)) {
                    Text(
                        text = if (inFavoriteList(favorites, movie.title)) stringResource(R.string.remove_favorite_button_text) else stringResource(R.string.add_favorite_button_text),
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

fun inFavoriteList(movies: List<Favorite>, query: String): Boolean {
    return movies.any { movie -> movie.title.contains(query, ignoreCase = true) }
}