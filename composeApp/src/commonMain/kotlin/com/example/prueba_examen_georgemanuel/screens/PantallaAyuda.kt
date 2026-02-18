package com.example.prueba_examen_georgemanuel.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PantallaAyuda(onOpenSepe: () -> Unit) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Ayuda",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = "Esta aplicación busca facilitarte la búsqueda de trabajo mediante la presentación de un minicurriculo. Buscar trabajo supone asumir un proceso activo, estructurado y exigente que requiere planificación, disciplina y estrategia, más que una simple búsqueda pasiva.  No se trata solo de enviar currículos, sino de gestionar una \"carrera profesional\" personal, entendiendo que es un trabajo en sí mismo. \n" +
                        "Buscar trabajo implica dedicar tiempo diario, establecer metas, mantener un horario y desarrollar hábitos como investigar empresas, redactar currículos personalizados y preparar entrevistas. \n" +
                        "El proceso incluye múltiples pasos: desde autoevaluarse (qué te gusta, qué habilidades tienes), localizar ofertas (a través de portales como Indeed, LinkedIn, o redes personales), enviar candidaturas, prepararse para entrevistas y mantener una red activa.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "¿Buscas trabajo?",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Button(
                    onClick = onOpenSepe,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ir al SEPE")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
