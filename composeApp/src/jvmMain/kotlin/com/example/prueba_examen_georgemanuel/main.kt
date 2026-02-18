package com.example.prueba_examen_georgemanuel

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Prueba_Examen_GeorgeManuel",
    ) {
        App()
    }
}