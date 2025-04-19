package fr.xania.legendspanel.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class ServerCreationData(
    val name: String,
    val jarPath: String,
    val targetDirectory: String
)

object ServerRepository {
    private val file = File("data/servers.json")
    private val json = Json { prettyPrint = true }

    fun saveServer(server: ServerCreationData) {
        val current = getServers().toMutableList()
        current.add(server)
        file.parentFile.mkdirs()
        file.writeText(json.encodeToString(ListSerializer(ServerCreationData.serializer()), current))
    }

    fun getServers(): List<ServerCreationData> {
        if (!file.exists()) return emptyList()
        return try {
            json.decodeFromString(file.readText())
        } catch (e: Exception) {
            emptyList()
        }
    }
}
