package com.example.ticketsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ticketsapp.ui.TicketsViewModel
import com.example.ticketsapp.ui.TicketsViewModelFactory
import com.example.ticketsapp.ui.screens.AddTicketScreen
import com.example.ticketsapp.ui.screens.CameraScreen
import com.example.ticketsapp.ui.screens.TicketDetailScreen
import com.example.ticketsapp.ui.screens.TicketsListScreen
import com.example.ticketsapp.ui.theme.TicketsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TicketsAppTheme {
                val navController = rememberNavController()

                val viewModel: TicketsViewModel = viewModel(
                    factory = TicketsViewModelFactory(application)
                )

                NavHost(
                    navController = navController,
                    startDestination = Routes.LIST
                ) {
                    composable(Routes.LIST) {
                        TicketsListScreen(
                            viewModel = viewModel,
                            onAddTicket = { navController.navigate(Routes.ADD) },
                            onTicketClick = { id ->
                                navController.navigate(Routes.detail(id))
                            }
                        )
                    }

                    composable(Routes.ADD) {
                        AddTicketScreen(
                            viewModel = viewModel,
                            onBack = { navController.popBackStack() },
                            onOpenCamera = { navController.navigate(Routes.CAMERA) }
                        )
                    }

                    composable(
                        route = Routes.DETAIL,
                        arguments = listOf(
                            navArgument("ticketId") {
                                type = NavType.LongType
                            }
                        )
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getLong("ticketId") ?: 0L
                        TicketDetailScreen(
                            viewModel = viewModel,
                            ticketId = id,
                            onBack = { navController.popBackStack() }
                        )
                    }

                    composable(Routes.CAMERA) {
                        CameraScreen(
                            viewModel = viewModel,
                            onPhotoTaken = {
                                navController.popBackStack() // regresa a pantalla de agregar
                            }
                        )
                    }
                }
            }
        }
    }
}
