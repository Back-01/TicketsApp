package com.example.ticketsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ticketsapp.data.TicketRepository
import com.example.ticketsapp.data.TicketsDatabase

class TicketsViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TicketsViewModel::class.java)) {
            val db = TicketsDatabase.getInstance(application)
            val repo = TicketRepository(db.ticketDao())
            return TicketsViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
