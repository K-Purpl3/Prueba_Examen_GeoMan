package com.example.prueba_examen_georgemanuel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MainActivityHolder.context = applicationContext
        setContent {
            App()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MainActivityHolder.context = null
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
