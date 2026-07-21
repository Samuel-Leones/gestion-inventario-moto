package co.edu.unibarranquilla.gestioninventario

import co.edu.unibarranquilla.gestioninventario.config.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class GestionInventarioApplication

fun main(args: Array<String>) {
    runApplication<GestionInventarioApplication>(*args)
}