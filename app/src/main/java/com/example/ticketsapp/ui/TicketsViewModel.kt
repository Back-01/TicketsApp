package com.example.ticketsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketsapp.data.Ticket
import com.example.ticketsapp.data.TicketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TicketsViewModel(
    private val repository: TicketRepository
) : ViewModel() {

    val tickets: StateFlow<List<Ticket>> =
        repository.getAllTickets()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    private val _currentTicket = MutableStateFlow<Ticket?>(null)
    val currentTicket: StateFlow<Ticket?> = _currentTicket

    // Ruta de la última imagen capturada
    private val _ultimaRutaImagen = MutableStateFlow<String?>(null)
    val ultimaRutaImagen: StateFlow<String?> = _ultimaRutaImagen

    // Último texto detectado por OCR
    private val _ultimoTextoDetectado = MutableStateFlow<String?>(null)
    val ultimoTextoDetectado: StateFlow<String?> = _ultimoTextoDetectado

    fun loadTicket(id: Long) {
        viewModelScope.launch {
            _currentTicket.value = repository.getTicket(id)
        }
    }

    fun addTicket(
        titulo: String,
        monto: Double,
        fecha: String,
        rutaImagen: String?
    ) {
        viewModelScope.launch {
            val ticket = Ticket(
                titulo = titulo,
                monto = monto,
                fecha = fecha,
                rutaImagen = rutaImagen
            )
            repository.addTicket(ticket)
            // limpiamos datos temporales
            _ultimaRutaImagen.value = null
            _ultimoTextoDetectado.value = null
        }
    }

    fun deleteTicket(id: Long) {
        viewModelScope.launch {
            repository.deleteTicket(id)
        }
    }

    fun setUltimaRutaImagen(path: String?) {
        _ultimaRutaImagen.value = path
    }

    fun setUltimoTextoDetectado(text: String?) {
        _ultimoTextoDetectado.value = text
    }
}
