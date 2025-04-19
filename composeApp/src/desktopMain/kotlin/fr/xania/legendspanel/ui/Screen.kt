package fr.xania.legendspanel.ui

import fr.xania.legendspanel.data.ServerCreationData

sealed class Screen {
    object CreateServer : Screen()
    data class ManageServer(val server: ServerCreationData) : Screen()
}