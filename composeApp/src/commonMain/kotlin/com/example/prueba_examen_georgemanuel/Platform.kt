package com.example.prueba_examen_georgemanuel

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform