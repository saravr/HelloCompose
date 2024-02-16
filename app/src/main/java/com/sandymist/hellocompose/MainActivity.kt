package com.sandymist.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.sandymist.hellocompose.ui.theme.HelloComposeTheme
import com.sandymist.hellocompose.viewmodel.NamesViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val namesViewModel = NamesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            HelloComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //ListScreen(namesViewModel)
                    val listItems = namesViewModel.listItems.collectAsState()
                    AnimatedListScreen(listItems.value) {
                        scope.launch {
                            namesViewModel.remove(it)
                        }
                    }
                    //NavContent()
                }
            }
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