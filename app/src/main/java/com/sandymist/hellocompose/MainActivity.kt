package com.sandymist.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sandymist.hellocompose.screens.NavScreenA
import com.sandymist.hellocompose.screens.NavScreenB
import com.sandymist.hellocompose.screens.NavScreenC
import com.sandymist.hellocompose.ui.theme.HelloComposeTheme
//import com.sandymist.hellocompose.viewmodel.NamesViewModel

class MainActivity : ComponentActivity() {
    //private val namesViewModel = NamesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //ListScreen(namesViewModel)
                    NavContent()
                }
            }
        }
    }
}

@Composable
fun NavContent() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "navA"
    ) {
        composable("navA") {
            NavScreenA(
                goBack = { navController.popBackStack() },
                navigateToB = { navController.navigate("navB") },
            )
        }
        composable("navB") {
            NavScreenB(
                goBack = { navController.popBackStack() },
                navigateToC = { navController.navigate("navC") },
            )
        }
        composable("navC") {
            NavScreenC(
                goBack = { navController.popBackStack() },
                navigateToA = { navController.navigate("navA") },
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    HelloComposeTheme {
        //    painter = rememberAsyncImagePainter("https://www.example.com/image.jpg"),
    }
}