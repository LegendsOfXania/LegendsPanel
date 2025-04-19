package fr.xania.legendspanel.logic

import fr.xania.legendspanel.data.ServerCreationData
import java.io.File

fun createServer(data: ServerCreationData) {
    val targetDir = File(data.targetDirectory, data.name)
    if (!targetDir.exists()) targetDir.mkdirs()

    val targetJar = File(targetDir, "server.jar")
    File(data.jarPath).copyTo(targetJar, overwrite = true)

    File(targetDir, "eula.txt").writeText("eula=true")
    File(targetDir, "server.properties").writeText("server-name=${data.name}")

    val launchCommand = "java -Xms1G -Xmx2G -jar server.jar nogui"
    File(targetDir, "start.sh").writeText("#!/bin/bash\n$launchCommand\n")
    File(targetDir, "start.bat").writeText("@echo off\n$launchCommand\n")

    println("[DEBUG] Serveur créé dans ${targetDir.absolutePath}")
}
