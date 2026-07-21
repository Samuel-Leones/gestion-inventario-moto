package co.edu.unibarranquilla.gestioninventario.dto

import co.edu.unibarranquilla.gestioninventario.model.Role

data class AuthResponse(

    val token: String,

    val type: String = "Bearer",

    val id: Long,

    val fullName: String,

    val email: String,

    val role: Role
)