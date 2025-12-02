package com.example.ticketsapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Ticket::class],
    version = 1,
    exportSchema = false
)
abstract class TicketsDatabase : RoomDatabase() {

    abstract fun ticketDao(): TicketDao

    companion object {
        @Volatile
        private var INSTANCE: TicketsDatabase? = null

        fun getInstance(context: Context): TicketsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TicketsDatabase::class.java,
                    "tickets_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
