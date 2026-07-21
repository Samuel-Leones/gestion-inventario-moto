package co.edu.unibarranquilla.gestioninventario.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.jwt")
data class JwtProperties(

    var secret: String = "",

    var expirationMinutes: Long = 30

)