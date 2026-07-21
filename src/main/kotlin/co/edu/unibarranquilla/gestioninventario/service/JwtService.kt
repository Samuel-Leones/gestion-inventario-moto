package co.edu.unibarranquilla.gestioninventario.service

import co.edu.unibarranquilla.gestioninventario.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService(
    private val jwtProperties: JwtProperties
) {

    private fun getSignKey(): SecretKey {
        return Keys.hmacShaKeyFor(
            jwtProperties.secret.toByteArray(StandardCharsets.UTF_8)
        )
    }

    fun generateToken(email: String): String {

        val now = Date()

        val expiration = Date(
            now.time + jwtProperties.expirationMinutes * 60 * 1000
        )

        return Jwts.builder()
            .subject(email)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(getSignKey())
            .compact()
    }

    fun extractEmail(token: String): String {

        return extractClaim(token) {
            it.subject
        }
    }

    fun isTokenValid(token: String, email: String): Boolean {

        return extractEmail(token) == email &&
                !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {

        return extractAllClaims(token)
            .expiration
            .before(Date())
    }

    private fun <T> extractClaim(
        token: String,
        resolver: (Claims) -> T
    ): T {

        return resolver(extractAllClaims(token))
    }

    private fun extractAllClaims(token: String): Claims {

        return Jwts
            .parser()
            .verifyWith(getSignKey())
            .build()
            .parseSignedClaims(token)
            .payload
    }

}