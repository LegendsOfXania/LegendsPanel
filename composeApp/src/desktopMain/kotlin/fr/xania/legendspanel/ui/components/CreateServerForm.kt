package fr.xania.legendspanel.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.xania.legendspanel.data.ServerCreationData
import fr.xania.legendspanel.data.ServerRepository
import fr.xania.legendspanel.logic.createServer
import fr.xania.legendspanel.ui.animation.TypewriterText
import java.io.File
import java.awt.FileDialog
import java.awt.Frame
import javax.swing.JFileChooser

@Composable
fun CreateServerForm(onCreate: (ServerCreationData) -> Unit) {
    var name by remember { mutableStateOf("") }
    var jarPath by remember { mutableStateOf("") }
    var targetFolder by remember { mutableStateOf("") }

    val isNameExists = name.isNotBlank() && targetFolder.isNotBlank() && File(targetFolder, name).exists()
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
            TypewriterText(
                text = "Créer un serveur",
                speed = 50,
                style = TextStyle(fontSize = 26.sp, color = Color.White, fontWeight = FontWeight.Bold)
            )

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
                    focusedBorderColor = if (isNameExists) Color.Red else MaterialTheme.colors.primary,
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
                Text(if (jarPath.isEmpty()) "Choisir un fichier JAR" else "JAR sélectionné")
            }

            Button(
                onClick = {
                    targetFolder = selectFolderDialog("Choisir le dossier de destination")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (targetFolder.isEmpty()) "Choisir un dossier cible" else "${File(targetFolder).name}")
            }

            Button(
                onClick = {
                    if (isFormValid) {
                        onCreate(ServerCreationData(name, jarPath, targetFolder))
                        createServer(ServerCreationData(name, jarPath, targetFolder))
                        ServerRepository.saveServer(ServerCreationData(name, jarPath, targetFolder))
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

            TextButton(
                onClick = {
                    val selectedFolder = selectFolderDialog("Choisir un dossier serveur existant")
                    if (selectedFolder.isNotBlank()) {
                        val name = File(selectedFolder).name
                        val jar = File(selectedFolder).walkTopDown().firstOrNull { it.extension == "jar" }?.absolutePath.orEmpty()
                        if (jar.isNotEmpty()) {
                            onCreate(ServerCreationData(name, jar, File(selectedFolder).parent))
                        } else {
                            println("Aucun .jar trouvé dans le dossier sélectionné.")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ouvrir un serveur existant")
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