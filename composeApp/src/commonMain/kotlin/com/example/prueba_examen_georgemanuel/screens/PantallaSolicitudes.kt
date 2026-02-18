package com.example.prueba_examen_georgemanuel.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.prueba_examen_georgemanuel.data.Person
import com.example.prueba_examen_georgemanuel.data.PersonRepository

@Composable
fun PantallaSolicitudes() {
    var filtroTexto by remember { mutableStateOf("") }
    var filtroActivo by remember { mutableStateOf("") }
    var personaExpandida by remember { mutableStateOf<Person?>(null) }

    val personasFiltradas = remember(filtroActivo, PersonRepository.persons.size) {
        if (filtroActivo.isBlank()) {
            PersonRepository.persons.toList()
        } else {
            PersonRepository.persons.filter {
                it.nombre.contains(filtroActivo, ignoreCase = true) ||
                        it.apellidos.contains(filtroActivo, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        //filtrar solicitudes
        Text(
            "Filtrar solicitudes",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        OutlinedTextField(
            value = filtroTexto,
            onValueChange = { filtroTexto = it },
            label = { Text("Filtrar por nombre o apellidos") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { filtroActivo = filtroTexto },
                modifier = Modifier.weight(1f)
            ) {
                Text("Aplicar filtro")
            }
            OutlinedButton(
                onClick = {
                    filtroTexto = ""
                    filtroActivo = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Eliminar filtro")
            }
        }

        HorizontalDivider()

        //lista
        if (PersonRepository.persons.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "No hay solicitudes registradas",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        "Ir a innscripciones para añadir personas",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                    )
                }
            }
        } else if (personasFiltradas.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No se encontraron resultados para \"$filtroActivo\"",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        } else {
            Text(
                "${personasFiltradas.size} solicitud(es)",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(personasFiltradas, key = { it.dni }) { persona ->
                    SolicitudCard(
                        persona = persona,
                        expandida = personaExpandida == persona,
                        onToggleExpand = {
                            personaExpandida = if (personaExpandida == persona) null else persona
                        },
                        onDelete = {
                            if (personaExpandida == persona) personaExpandida = null
                            PersonRepository.removePerson(persona)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SolicitudCard(
    persona: Person,
    expandida: Boolean,
    onToggleExpand: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (expandida)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            //fila de nombre
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleExpand() }
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${persona.nombre} ${persona.apellidos}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "DNI: ${persona.dni} · ${persona.edad} años",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                Icon(
                    imageVector = if (expandida) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expandida) "Colapsar" else "Expandir",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            //detalle expandible
            AnimatedVisibility(
                visible = expandida,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(4.dp))
                    DetalleItem("Sexo", persona.sexo)
                    DetalleItem("Dirección", persona.direccion)
                    DetalleItem("Email", persona.email)
                    DetalleItem("Teléfono", persona.telefono)
                    if (persona.habilidades.isNotBlank()) {
                        DetalleItem("Habilidades", persona.habilidades)
                    }
                }
            }
        }
    }
}

@Composable
private fun DetalleItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.width(90.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )
    }
}
