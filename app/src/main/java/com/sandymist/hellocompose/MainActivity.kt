package com.sandymist.hellocompose

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import com.sandymist.hellocompose.ui.theme.HelloComposeTheme
import com.sandymist.hellocompose.viewmodel.NamesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val namesViewModel = NamesViewModel()

    @SuppressLint("CoroutineCreationDuringComposition")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            HelloComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //ListScreen(namesViewModel)
//                    val listItems = namesViewModel.listItems.collectAsState()
//                    AnimatedListScreen(listItems.value) {
//                        scope.launch {
//                            namesViewModel.remove(it)
//                        }
//                    }
                    var showText by remember { mutableStateOf(false) }

                    CoroutineScope(Dispatchers.Main).launch {
                        delay(5000L)
                        showText = true
                    }

                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val (content, text) = createRefs()
                        NavContent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .constrainAs(content) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(text.top)
                                }
                        )
                        if (showText) {
                            Text(
                                "Testing ...",
                                modifier = Modifier
                                    .height(50.dp)
                                    .fillMaxWidth()
                                    .constrainAs(text) {
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        top.linkTo(content.bottom)
                                        bottom.linkTo(parent.bottom)
                                    }
                                    .background(Color.Yellow)
                            )
                        }
                    }
                }
            }
        }
        permissions()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun permissions() {
        val permission = android.Manifest.permission.POST_NOTIFICATIONS

        if (ContextCompat.checkSelfPermission(this, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission has not been granted
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val permissionRequested = sharedPreferences.getBoolean("permission_requested", false)

            if (!permissionRequested) {
                // Permission request has not been shown before
                // Show the permission request dialog
                requestPermissions(this, arrayOf(permission), 100)

                // Update the flag to indicate that the permission request has been shown
                sharedPreferences.edit().putBoolean("permission_requested", true).apply()
            }
        } else {
            // Permission has already been granted
            // Continue with your app logic
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