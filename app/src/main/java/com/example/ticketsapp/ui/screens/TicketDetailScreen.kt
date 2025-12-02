package com.example.ticketsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ticketsapp.ui.TicketsViewModel
import coil.compose.AsyncImage
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketDetailScreen(
    viewModel: TicketsViewModel,
    ticketId: Long,
    onBack: () -> Unit
) {
    val ticketState = viewModel.currentTicket.collectAsState()

    LaunchedEffect(ticketId) {
        viewModel.loadTicket(ticketId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del ticket") }
            )
        }
    ) { padding ->
        val ticket = ticketState.value

        if (ticket == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                Text("Cargando...")
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(ticket.titulo, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Monto: $${ticket.monto}")
                Text("Fecha: ${ticket.fecha}")

                Spacer(modifier = Modifier.height(16.dp))

                if (ticket.rutaImagen == null) {
                    Text("Sin imagen asociada todavía.")
                } else {
                    Text("Foto del ticket:", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))

                    AsyncImage(
                        model = File(ticket.rutaImagen),
                        contentDescription = "Foto del ticket",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        viewModel.deleteTicket(ticket.id)
                        onBack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Eliminar")
                }
            }
        }
    }
}
