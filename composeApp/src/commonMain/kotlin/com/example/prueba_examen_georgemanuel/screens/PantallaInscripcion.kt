package com.example.prueba_examen_georgemanuel.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.prueba_examen_georgemanuel.data.Person
import com.example.prueba_examen_georgemanuel.data.PersonRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInscripcion() {
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var edadPersona by remember { mutableStateOf(18) }
    var sexoPersona by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var habilidades by remember { mutableStateOf("") }

    // por si hay errores
    var errorNombre by remember { mutableStateOf(false) }
    var errorApellidos by remember { mutableStateOf(false) }
    var errorDni by remember { mutableStateOf(false) }
    var errorSexo by remember { mutableStateOf(false) }
    var errorDireccion by remember { mutableStateOf(false) }
    var errorEmail by remember { mutableStateOf(false) }
    var errorTelefono by remember { mutableStateOf(false) }

    // Dropdowns
    var edadExpanded by remember { mutableStateOf(false) }
    var sexoExpanded by remember { mutableStateOf(false) }

    // Snackbar
    var snackbarMessage by remember { mutableStateOf("") }
    var showSnackbar by remember { mutableStateOf(false) }

    val edades = (18..67).toList()
    val opcionesSexo = listOf("Masculino", "Femenino", "Nunca", "Todos los dias", "Celibato", "Therian", "Otro", "No desea compartirlo")

    fun validarDni(dni: String): Boolean {
        val regex = Regex("^[0-9]{8}[A-Za-z]$")
        return regex.matches(dni)
    }

    fun validarEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    fun validarTelefono(tel: String): Boolean {
        return tel.length in 9..15 && tel.all { it.isDigit() || it == '+' }
    }

    fun limpiarCampos() {
        nombre = ""; apellidos = ""; dni = ""; edadPersona = 18
        sexoPersona = ""; direccion = ""; email = ""; telefono = ""; habilidades = ""
        errorNombre = false; errorApellidos = false; errorDni = false; errorSexo = false
        errorDireccion = false; errorEmail = false; errorTelefono = false
    }

    fun guardar() {
        errorNombre = nombre.isBlank()
        errorApellidos = apellidos.isBlank()
        errorDni = !validarDni(dni)
        errorSexo = sexoPersona.isBlank()
        errorDireccion = direccion.isBlank()
        errorEmail = !validarEmail(email)
        errorTelefono = !validarTelefono(telefono)

        if (!errorNombre && !errorApellidos && !errorDni && !errorSexo &&
            !errorDireccion && !errorEmail && !errorTelefono
        ) {
            PersonRepository.addPerson(
                Person(
                    nombre = nombre,
                    apellidos = apellidos,
                    dni = dni,
                    edad = edadPersona,
                    sexo = sexoPersona,
                    direccion = direccion,
                    email = email,
                    telefono = telefono,
                    habilidades = habilidades
                )
            )
            snackbarMessage = "Persona guardada correctamente"
            showSnackbar = true
            limpiarCampos()
        } else {
            snackbarMessage = "Corrige los errores del formulario"
            showSnackbar = true
        }
    }

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Datos Personales",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            //nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it; errorNombre = false },
                label = { Text("Nombre *") },
                isError = errorNombre,
                supportingText = { if (errorNombre) Text("El nombre es obligatorio") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            //apellidos
            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it; errorApellidos = false },
                label = { Text("Apellidos *") },
                isError = errorApellidos,
                supportingText = { if (errorApellidos) Text("Los apellidos son obligatorios") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            //deni
            OutlinedTextField(
                value = dni,
                onValueChange = { dni = it.uppercase(); errorDni = false },
                label = { Text("DNI * (8 dígitos + letra)") },
                isError = errorDni,
                supportingText = { if (errorDni) Text("DNI inválido. Formato: 12345678A") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            //edad
            ExposedDropdownMenuBox(
                expanded = edadExpanded,
                onExpandedChange = { edadExpanded = !edadExpanded }
            ) {
                OutlinedTextField(
                    value = edadPersona.toString(),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Edad *") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = edadExpanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = edadExpanded,
                    onDismissRequest = { edadExpanded = false }
                ) {
                    edades.forEach { edad ->
                        DropdownMenuItem(
                            text = { Text(edad.toString()) },
                            onClick = {
                                edadPersona = edad
                                edadExpanded = false
                            }
                        )
                    }
                }
            }

            //sexo
            ExposedDropdownMenuBox(
                expanded = sexoExpanded,
                onExpandedChange = { sexoExpanded = !sexoExpanded }
            ) {
                OutlinedTextField(
                    value = sexoPersona,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Sexo *") },
                    isError = errorSexo,
                    supportingText = { if (errorSexo) Text("Selecciona una opción") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = sexoExpanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = sexoExpanded,
                    onDismissRequest = { sexoExpanded = false }
                ) {
                    opcionesSexo.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                sexoPersona = opcion
                                sexoExpanded = false
                                errorSexo = false
                            }
                        )
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                "Datos de Contacto",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            //direccion
            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it; errorDireccion = false },
                label = { Text("Dirección *") },
                isError = errorDireccion,
                supportingText = { if (errorDireccion) Text("La dirección es obligatoria") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            //email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it; errorEmail = false },
                label = { Text("Email *") },
                isError = errorEmail,
                supportingText = { if (errorEmail) Text("Email inválido") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            //telefono
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it; errorTelefono = false },
                label = { Text("Teléfono *") },
                isError = errorTelefono,
                supportingText = { if (errorTelefono) Text("Teléfono inválido (9-15 dígitos)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                "Perfil Profesional",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            //habilidades
            OutlinedTextField(
                value = habilidades,
                onValueChange = { habilidades = it },
                label = { Text("Habilidades") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                maxLines = 6,
                placeholder = { Text("Describe tus habilidades, experiencia y competencias...") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            //botones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { limpiarCampos() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Borrar")
                }
                Button(
                    onClick = { guardar() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Guardar")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        //bottombar
        if (showSnackbar) {
            LaunchedEffect(snackbarMessage) {
                kotlinx.coroutines.delay(3000)
                showSnackbar = false
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Snackbar(
                    dismissAction = {
                        TextButton(onClick = { showSnackbar = false }) {
                            Text("OK")
                        }
                    }
                ) {
                    Text(snackbarMessage)
                }
            }
        }
    }
}
