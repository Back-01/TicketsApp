package com.example.ticketsapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {

    @Query("SELECT * FROM tickets ORDER BY id DESC")
    fun getAllTickets(): Flow<List<Ticket>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: Ticket)

    @Query("SELECT * FROM tickets WHERE id = :id")
    suspend fun getTicketById(id: Long): Ticket?

    @Query("DELETE FROM tickets WHERE id = :id")
    suspend fun deleteTicket(id: Long)
}
