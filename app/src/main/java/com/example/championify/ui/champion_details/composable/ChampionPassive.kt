package com.example.championify.ui.champion_details.composable

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.championify.data.util.Constant
import com.example.championify.domain.model.PassiveModel

@Composable
fun ChampionPassive(
    passive: PassiveModel,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = {
            Text(
                text = passive.name ?: ""
            )
        },
        leadingContent = {
            AsyncImage(
                model = Constant.IMAGE_PASSIVE_URL + "${passive.image?.full}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        },
        supportingContent = {
            Text(
                text = passive.description ?: ""
            )
        },
        modifier = modifier
    )
}