package com.example.ticketsapp.data

import kotlinx.coroutines.flow.Flow

class TicketRepository(
    private val dao: TicketDao
) {
    fun getAllTickets(): Flow<List<Ticket>> = dao.getAllTickets()

    suspend fun addTicket(ticket: Ticket) = dao.insertTicket(ticket)

    suspend fun getTicket(id: Long) = dao.getTicketById(id)

    suspend fun deleteTicket(id: Long) = dao.deleteTicket(id)
}
