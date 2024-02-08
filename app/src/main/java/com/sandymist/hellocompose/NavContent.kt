package com.sandymist.hellocompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sandymist.hellocompose.screens.NavScreenA
import com.sandymist.hellocompose.screens.NavScreenB
import com.sandymist.hellocompose.screens.NavScreenC
import com.sandymist.hellocompose.screens.NavScreenC1

@Composable
fun NavContent() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "navA"
    ) {
        composable("navA") {
            NavScreenA(
                previousRoute = navController.previousBackStackEntry?.destination?.route ?: "None",
                goBack = { navController.popBackStack() },
                navigateToB = { navController.navFromRoot("navB") },
            )
        }
        composable("navB") {
            NavScreenB(
                previousRoute = navController.previousBackStackEntry?.destination?.route ?: "None",
                goBack = { navController.popBackStack() },
                navigateToC = { navController.navFromRoot("navC") },
            )
        }
        composable("navC") {
            NavScreenC(
                previousRoute = navController.previousBackStackEntry?.destination?.route ?: "None",
                goBack = { navController.popBackStack() },
                navigateToC1 = { navController.navigate("navC1") },
            )
        }
        composable("navC1") {
            NavScreenC1(
                previousRoute = navController.previousBackStackEntry?.destination?.route ?: "None",
                goBack = { navController.popBackStack() },
                navigateToA = { navController.navigate("navA") },
            )
        }
    }
}

fun NavController.navFromRoot(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
