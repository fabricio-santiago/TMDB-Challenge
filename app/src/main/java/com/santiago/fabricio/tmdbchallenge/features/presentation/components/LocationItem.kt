package com.santiago.fabricio.tmdbchallenge.features.presentation.components

import android.os.Build
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import com.santiago.fabricio.tmdbchallenge.R

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun LocationItem(
//    location: Location,
//) {
//
//    val context = LocalContext.current
//
//    val expanded by remember {
//        mutableStateOf(false)
//    }
//    OutlinedCard(
//        modifier = Modifier
//            .padding(4.dp)
//            .fillMaxWidth()
//            .clearAndSetSemantics {
//                contentDescription =
//                    context.getString(R.string.locations_item_description_outlined_card)
//            },
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.background,
//        ),
//        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
//        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
//        elevation = CardDefaults.cardElevation(8.dp)
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Column(modifier = Modifier.padding(12.dp)) {
//                Text(
//                    text = location.name,
//                    style = MaterialTheme.typography.titleLarge,
//                    color = MaterialTheme.colorScheme.secondary,
//                    modifier = Modifier.clearAndSetSemantics {
//                        contentDescription =
//                            context.getString(R.string.locations_item_description_name)
//                    }
//                )
//
//                Spacer(modifier = Modifier.size(8.dp))
//
//                Text(
//                    text = String.format("Type: %s", location.type),
//                    style = MaterialTheme.typography.labelSmall,
//                    color = MaterialTheme.colorScheme.secondary,
//                    modifier = Modifier.clearAndSetSemantics {
//                        contentDescription =
//                            context.getString(R.string.locations_item_description_type)
//                    },
//                    maxLines = if (!expanded) 4 else Int.MAX_VALUE
//                )
//            }
//        }
//    }
//}