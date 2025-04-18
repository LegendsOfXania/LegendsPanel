package fr.xania.legendspanel.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.xania.legendspanel.data.ServerCreationData
import java.io.File
import java.awt.FileDialog
import java.awt.Frame
import javax.swing.JFileChooser

@Composable
fun CreateServerForm(onCreate: (ServerCreationData) -> Unit) {
    var name by remember { mutableStateOf("") }
    var jarPath by remember { mutableStateOf("") }
    var targetFolder by remember { mutableStateOf("") }

    val isNameExists = name.isNotBlank() && name == "existingServer"
    val isFormValid = name.isNotBlank() && jarPath.isNotBlank() && targetFolder.isNotBlank() && !isNameExists

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(400.dp)
                .background(
                    color = Color(0xAA1E1E1E),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(
                        "Nom du serveur",
                        color = if (isNameExists) Color.Red else Color.White
                    )
                },
                isError = isNameExists,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = if (isNameExists) Color.Red else Color(0xFF6200EE),
                    unfocusedBorderColor = if (isNameExists) Color.Red else Color.White,
                    cursorColor = Color.White
                )
            )

            Button(
                onClick = {
                    jarPath = selectFileDialog("Sélectionne un fichier JAR", "*.jar")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (jarPath.isEmpty()) "Choisir un fichier JAR" else "✅ JAR sélectionné")
            }

            Button(
                onClick = {
                    targetFolder = selectFolderDialog("Choisir le dossier de destination")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (targetFolder.isEmpty()) "Choisir un dossier cible" else "📁 ${targetFolder.takeLast(20)}")
            }

            Button(
                onClick = {
                    if (isFormValid) {
                        onCreate(ServerCreationData(name, jarPath, targetFolder))
                    }
                },
                enabled = isFormValid,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isNameExists) Color.Red else MaterialTheme.colors.primary
                )
            ) {
                Text(if (isNameExists) "Nom déjà pris" else "Créer le serveur")
            }
        }
    }
}

fun selectFileDialog(title: String, extension: String): String {
    val fileDialog = FileDialog(null as Frame?, title, FileDialog.LOAD)
    fileDialog.file = extension
    fileDialog.isVisible = true
    return fileDialog.file?.let { File(fileDialog.directory, it).absolutePath } ?: ""
}

fun selectFolderDialog(title: String): String {
    val chooser = JFileChooser()
    chooser.dialogTitle = title
    chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
    val result = chooser.showOpenDialog(null)
    return if (result == JFileChooser.APPROVE_OPTION) chooser.selectedFile.absolutePath else ""
}
