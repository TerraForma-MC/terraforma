package com.terraforma.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.terraforma.app.ui.development.DevelopmentScreen
import com.terraforma.app.ui.home.HomeScreen
import com.terraforma.app.ui.play.PlayMinecraftScreen
import com.terraforma.app.ui.servers.RunServersScreen
import com.terraforma.app.ui.sessions.SessionsScreen
import com.terraforma.app.ui.settings.SettingsScreen
import com.terraforma.app.ui.storage.StorageScreen
import com.terraforma.app.ui.terminal.TerminalScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }
        composable(Routes.PLAY_MINECRAFT) {
            PlayMinecraftScreen(navController = navController)
        }
        composable(Routes.RUN_SERVERS) {
            RunServersScreen(navController = navController)
        }
        composable(Routes.DEVELOPMENT) {
            DevelopmentScreen(navController = navController)
        }
        composable(Routes.SESSIONS) {
            SessionsScreen(navController = navController)
        }
        composable(Routes.TERMINAL) {
            TerminalScreen(navController = navController)
        }
        composable(Routes.STORAGE) {
            StorageScreen(navController = navController)
        }
        composable(Routes.SETTINGS) {
            SettingsScreen(navController = navController)
        }
    }
}