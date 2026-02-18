package com.example.prueba_examen_georgemanuel

import com.example.prueba_examen_georgemanuel.data.Person
import com.example.prueba_examen_georgemanuel.data.PersonRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ComposeAppCommonTest {

    // --- Test 1: Validación de DNI ---
    @Test
    fun testDniValidoConLetraYOchoDigitos() {
        val dniValido = "12345678A"
        val regex = Regex("^[0-9]{8}[A-Za-z]$")
        assertTrue(regex.matches(dniValido), "El DNI '12345678A' debería ser válido")
    }

    @Test
    fun testDniInvalidoSinLetra() {
        val dniInvalido = "123456789"
        val regex = Regex("^[0-9]{8}[A-Za-z]$")
        assertFalse(regex.matches(dniInvalido), "El DNI '123456789' no debería ser válido (sin letra)")
    }

    @Test
    fun testDniInvalidoMuyCorto() {
        val dniInvalido = "1234A"
        val regex = Regex("^[0-9]{8}[A-Za-z]$")
        assertFalse(regex.matches(dniInvalido), "El DNI '1234A' no debería ser válido (muy corto)")
    }

    // --- Test 2: Validación de Email ---
    @Test
    fun testEmailValidoConArrobaYPunto() {
        val email = "usuario@ejemplo.com"
        val esValido = email.contains("@") && email.contains(".")
        assertTrue(esValido, "El email 'usuario@ejemplo.com' debería ser válido")
    }

    @Test
    fun testEmailInvalidoSinArroba() {
        val email = "usuarioejemplo.com"
        val esValido = email.contains("@") && email.contains(".")
        assertFalse(esValido, "El email sin '@' no debería ser válido")
    }

    // --- Test 3: PersonRepository ---
    @Test
    fun testAgregarPersonaAlRepositorio() {
        PersonRepository.clear()
        val persona = Person(
            nombre = "Ana",
            apellidos = "García López",
            dni = "87654321B",
            edad = 30,
            sexo = "Femenino",
            direccion = "Calle Mayor 1",
            email = "ana@test.com",
            telefono = "600123456",
            habilidades = "Kotlin, Android"
        )
        PersonRepository.addPerson(persona)
        assertEquals(1, PersonRepository.persons.size, "El repositorio debería tener 1 persona")
        assertEquals("Ana", PersonRepository.persons[0].nombre)
        PersonRepository.clear()
    }

    @Test
    fun testEliminarPersonaDelRepositorio() {
        PersonRepository.clear()
        val persona = Person(
            nombre = "Carlos",
            apellidos = "Martínez",
            dni = "11223344C",
            edad = 25,
            sexo = "Masculino",
            direccion = "Av. Libertad 5",
            email = "carlos@test.com",
            telefono = "611222333",
            habilidades = ""
        )
        PersonRepository.addPerson(persona)
        assertEquals(1, PersonRepository.persons.size)
        PersonRepository.removePerson(persona)
        assertEquals(0, PersonRepository.persons.size, "El repositorio debería estar vacío tras eliminar")
        PersonRepository.clear()
    }

    @Test
    fun testRepositorioVacioAlInicio() {
        PersonRepository.clear()
        assertEquals(0, PersonRepository.persons.size, "El repositorio debería estar vacío tras clear()")
    }

    // --- Test 4: Validación de Teléfono ---
    @Test
    fun testTelefonoValidoNueveDigitos() {
        val telefono = "612345678"
        val esValido = telefono.length in 9..15 && telefono.all { it.isDigit() || it == '+' }
        assertTrue(esValido, "El teléfono '612345678' debería ser válido")
    }

    @Test
    fun testTelefonoInvalidoMuyCorto() {
        val telefono = "1234"
        val esValido = telefono.length in 9..15 && telefono.all { it.isDigit() || it == '+' }
        assertFalse(esValido, "El teléfono '1234' no debería ser válido (muy corto)")
    }

    // --- Test 5: Rango de edad ---
    @Test
    fun testEdadDentroDelRangoPermitido() {
        val edades = (18..67).toList()
        assertTrue(edades.contains(18), "La edad mínima 18 debería estar en el rango")
        assertTrue(edades.contains(67), "La edad máxima 67 debería estar en el rango")
        assertFalse(edades.contains(17), "La edad 17 no debería estar en el rango")
        assertFalse(edades.contains(68), "La edad 68 no debería estar en el rango")
        assertEquals(50, edades.size, "El rango debería tener 50 opciones (18 a 67 inclusive)")
    }
}