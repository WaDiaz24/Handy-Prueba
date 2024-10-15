package handy

import java.nio.file.Files
import java.nio.file.Paths

class BootStrap {

    def init = { servletContext ->
        loadEnvFile()
    }
    def destroy = {
    }

    def loadEnvFile() {
        def envFilePath = Paths.get(".env")

        if (Files.exists(envFilePath)) {
            def apiTokenLine = Files.lines(envFilePath)
                    .filter { it.startsWith("API_TOKEN=") }
                    .findFirst()

            if (apiTokenLine.isPresent()) {
                def apiToken = apiTokenLine.get().split("=")[1].trim()

                System.setProperty("API_TOKEN", apiToken)
            } else {
                println "API_TOKEN no encontrado en el archivo .env"
            }
        } else {
            println "Archivo .env no encontrado"
        }
    }
}
