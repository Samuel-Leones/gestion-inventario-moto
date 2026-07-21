package co.edu.unibarranquilla.gestioninventario.controller

import co.edu.unibarranquilla.gestioninventario.dto.LoginRequest
import co.edu.unibarranquilla.gestioninventario.dto.LoginResponse
import co.edu.unibarranquilla.gestioninventario.model.User
import co.edu.unibarranquilla.gestioninventario.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {


    @PostMapping("/register")
    fun register(
        @Valid @RequestBody user: User
    ): ResponseEntity<User> {

        val response = authService.register(user)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response)
    }


    @PostMapping("/login")
    fun login(
        @Valid @RequestBody request: LoginRequest
    ): ResponseEntity<LoginResponse> {

        val response = authService.login(request)

        return ResponseEntity.ok(response)
    }

}