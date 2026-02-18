package com.example.prueba_examen_georgemanuel.data

import androidx.compose.runtime.mutableStateListOf

data class Person(
    val nombre: String,
    val apellidos: String,
    val dni: String,
    val edad: Int,
    val sexo: String,
    val direccion: String,
    val email: String,
    val telefono: String,
    val habilidades: String
)

object PersonRepository {
    private val _persons = mutableStateListOf<Person>()
    val persons: List<Person> = _persons

    fun addPerson(person: Person) {
        _persons.add(person)
    }

    fun removePerson(person: Person) {
        _persons.remove(person)
    }

    fun clear() {
        _persons.clear()
    }
}
