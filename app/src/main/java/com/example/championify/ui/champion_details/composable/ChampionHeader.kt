package com.example.championify.ui.champion_details.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.championify.data.util.Constant
import com.example.championify.domain.model.ChampionModel

@Composable
fun ChampionHeader(
    champion: ChampionModel,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(
                text = champion.name ?: "",
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp
            )
        },
        supportingContent = {
            Text(
                text = champion.tags?.firstOrNull() ?: "",
            )
        },
        leadingContent = {
            AsyncImage(
                model = Constant.IMAGE_SQUARE_URL + "${champion.name}.png",
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        },
        trailingContent = {
            Text(
                text = champion.title ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    )
}