package com.example.championify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.championify.ui.champion_details.ChampionDetailsScreen
import com.example.championify.ui.champion_details.ChampionDetailsViewModel
import com.example.championify.ui.navigate.ChampionDetails
import com.example.championify.ui.navigate.ChampionList
import com.example.championify.ui.screen.champion_list.ChampionListScreen
import com.example.championify.ui.screen.champion_list.ChampionListViewModel
import com.example.championify.ui.theme.ChampionifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChampionifyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ChampionList
                ) {
                    composable<ChampionList> {
                        val viewModel = hiltViewModel<ChampionListViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        ChampionListScreen(
                            state = state,
                            onValueChange = viewModel::onSearchQueryChange,
                            navigate = { name ->
                                navController.navigate(ChampionDetails(name))
                            }
                        )
                    }
                    composable<ChampionDetails> {
                        val viewModel = hiltViewModel<ChampionDetailsViewModel>()
                        viewModel.champion.value?.let {
                            ChampionDetailsScreen(champion = it)
                        }
                    }
                }
            }
        }
    }
}
