package com.example.championify.ui.screen.champion_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.championify.domain.model.toChampionList
import com.example.championify.domain.repository.ApiRepository
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChampionListViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ChampionListState())
    val state = _state.asStateFlow()

    // Define a tag for logging
    companion object {
        private const val TAG = "ChampionListViewModel"
    }

    init {
        viewModelScope.launch {
            apiRepository.getChampions()
                .onSuccess {
                    Timber.d("Successfully fetched champions: ${data.champion}")
                    _state.update {
                        it.copy(
                            champions = data.champion.toChampionList()
                        )
                    }
                }
                .onError {
                    Timber.e("Error fetching champions: ${message()}")
                }
                .onException {
                    Timber.e("Exception while fetching champions: ${message}")
                }

        }
    }

    fun onSearchQueryChange(query: String) {
        _state.update {
            it.copy(
                searchQuery = query,
                filteredChampions = it.champions.filter { champion ->
                    champion.name?.contains(query, ignoreCase = true) == true
                }

            )
        }
    }
}