package com.example.ticketsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickets")
data class Ticket(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val titulo: String,
    val monto: Double,
    val fecha: String,
    val rutaImagen: String?
)
