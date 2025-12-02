package com.example.ticketsapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ticketsapp.data.Ticket
import com.example.ticketsapp.ui.TicketsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketsListScreen(
    viewModel: TicketsViewModel,
    onAddTicket: () -> Unit,
    onTicketClick: (Long) -> Unit
) {
    val tickets = viewModel.tickets.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Tickets") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTicket) {
                Text("+")
            }
        }
    ) { padding ->
        if (tickets.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay tickets. Pulsa + para agregar uno.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(tickets.value) { ticket ->
                    TicketItem(ticket = ticket, onClick = { onTicketClick(ticket.id) })
                }
            }
        }
    }
}

@Composable
private fun TicketItem(
    ticket: Ticket,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(ticket.titulo, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Monto: $${ticket.monto}")
            Text("Fecha: ${ticket.fecha}")
        }
    }
}
