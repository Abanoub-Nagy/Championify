package com.example.championify.ui.screen.champion_list

import com.example.championify.domain.model.ChampionModel

data class ChampionListState(
    val searchQuery: String = "",
    val champions: List<ChampionModel> = emptyList(),
    val filteredChampions: List<ChampionModel> = emptyList(),

)
