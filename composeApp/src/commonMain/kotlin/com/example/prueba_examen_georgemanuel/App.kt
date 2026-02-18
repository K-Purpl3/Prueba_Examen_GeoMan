package com.example.prueba_examen_georgemanuel

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.prueba_examen_georgemanuel.screens.*
import com.example.prueba_examen_georgemanuel.theme.EmpleoTheme

enum class Screen {
    Empleo, Inscripcion, Solicitudes, Ayuda
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    EmpleoTheme {
        var currentScreen by remember { mutableStateOf(Screen.Empleo) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            when (currentScreen) {
                                Screen.Empleo -> "Empleo"
                                Screen.Inscripcion -> "Inscripción"
                                Screen.Solicitudes -> "Solicitudes"
                                Screen.Ayuda -> "Ayuda"
                            }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentScreen == Screen.Empleo,
                        onClick = { currentScreen = Screen.Empleo },
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Empleo") },
                        label = { Text("Empleo") }
                    )
                    NavigationBarItem(
                        selected = currentScreen == Screen.Inscripcion,
                        onClick = { currentScreen = Screen.Inscripcion },
                        icon = { Icon(Icons.Filled.Edit, contentDescription = "Inscripción") },
                        label = { Text("Inscripción") }
                    )
                    NavigationBarItem(
                        selected = currentScreen == Screen.Solicitudes,
                        onClick = { currentScreen = Screen.Solicitudes },
                        icon = { Icon(Icons.Filled.List, contentDescription = "Solicitudes") },
                        label = { Text("Solicitudes") }
                    )
                    NavigationBarItem(
                        selected = currentScreen == Screen.Ayuda,
                        onClick = { currentScreen = Screen.Ayuda },
                        icon = { Icon(Icons.Filled.Info, contentDescription = "Ayuda") },
                        label = { Text("Ayuda") }
                    )
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (currentScreen) {
                    Screen.Empleo -> PantallaEmpleo()
                    Screen.Inscripcion -> PantallaInscripcion()
                    Screen.Solicitudes -> PantallaSolicitudes()
                    Screen.Ayuda -> PantallaAyuda(
                        onOpenSepe = { openUrl("https://www.sepe.es/HomeSepe/es/") }
                    )
                }
            }
        }
    }
}
