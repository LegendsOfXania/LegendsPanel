package fr.xania.legendspanel.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.xania.legendspanel.ui.layout.Header

@Composable
fun ServerManagerScreen() {
    Row(modifier = Modifier.fillMaxSize().padding(8.dp)) {

        Header(serverName = server.name)
        // Colonne gauche
        Column(
            modifier = Modifier
                .weight(0.25f)
                .fillMaxHeight()
                .background(Color(0xFF1E1E1E))
                .padding(8.dp)
        ) {
            Text("Tâches", color = Color.White)
            // TODO: Liste de tâches
        }

        // Colonne centre
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()
                .background(Color(0xFF2A2A2A))
                .padding(8.dp)
        ) {
            Text("Console", color = Color.White)
            // TODO: Affichage console, fichiers, logs...
        }

        // Colonne droite
        Column(
            modifier = Modifier
                .weight(0.25f)
                .fillMaxHeight()
                .background(Color(0xFF1E1E1E))
                .padding(8.dp)
        ) {
            Text("Système", color = Color.White)
            // TODO: CPU, RAM, statut du serveur
        }
    }
}

