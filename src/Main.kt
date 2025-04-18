import java.io.File
import java.util.*

fun main() {

    val serversDir = File("servers")
    if (!serversDir.exists()) {
        println("Le dossier 'servers' n'existe pas.")
        return
    }

    val serverList = serversDir.listFiles { file -> file.isDirectory } ?: emptyArray()
    if (serverList.isEmpty()) {
        println("Aucun serveur trouvé.")
        return
    }

    println("=== Liste des serveurs ===")
    serverList.forEachIndexed { index, file ->
        println("${index + 1}. ${file.name}")
    }

    print("Choisis un serveur à lancer : ")
    val choice = readlnOrNull()?.toIntOrNull()
    if (choice == null || choice !in 1..serverList.size) {
        println("Choix invalide.")
        return
    }

    val selected = serverList[choice - 1]
    val script = if (System.getProperty("os.name").startsWith("Windows")) {
        File(selected, "start.bat")
    } else {
        File(selected, "start.sh")
    }

    if (!script.exists()) {
        println("Script de démarrage introuvable : ${script.name}")
        return
    }

    println("Lancement de ${selected.name}...")
    val process = ProcessBuilder(script.absolutePath)
        .directory(selected)
        .redirectErrorStream(true)
        .start()

    val scanner = Scanner(process.inputStream)
    while (scanner.hasNextLine()) {
        println(scanner.nextLine())
    }

    val exitCode = process.waitFor()
    println("Le serveur s'est arrêté (code $exitCode).")
}
