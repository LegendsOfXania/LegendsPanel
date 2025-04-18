package fr.xania.legendspanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.xania.legendspanel.layout.ServerHeader

@Composable
fun PanelLayout() {
    Column(modifier = Modifier.fillMaxSize()) {

        ServerHeader(serverName = "Xania dev")

        // Contenu principal
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            // Colonne gauche
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight()
                    .background(Color.DarkGray)
                    .padding(8.dp)
            ) {
                // Exemple de contenu
                Text("Liste des tâches", color = Color.White)
            }

            Spacer(modifier = Modifier.width(24.dp))

            // Colonne centrale
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color(0xFF1E1E1E))
                    .padding(8.dp)
            ) {
                Text("Console / Fichiers", color = Color.White)
            }

            Spacer(modifier = Modifier.width(24.dp))

            // Colonne droite
            Column(
                modifier = Modifier
                    .width(250.dp)
                    .fillMaxHeight()
                    .background(Color.DarkGray)
                    .padding(8.dp)
            ) {
                Text("Monitoring", color = Color.White)
            }
        }
    }
}
