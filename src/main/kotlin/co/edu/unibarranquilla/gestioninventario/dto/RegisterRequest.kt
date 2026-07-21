package co.edu.unibarranquilla.gestioninventario.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class RegisterRequest(

    @field:NotBlank
    val nombre: String,

    @field:NotBlank
    val apellido: String,

    @field:Email
    val email: String,

    @field:NotBlank
    val password: String
)