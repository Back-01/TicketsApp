package com.example.ticketsapp

object Routes {
    const val LIST = "list"
    const val ADD = "add"
    const val CAMERA = "camera"
    const val DETAIL = "detail/{ticketId}"

    fun detail(ticketId: Long) = "detail/$ticketId"
}
