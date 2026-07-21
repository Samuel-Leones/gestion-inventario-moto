package co.edu.unibarranquilla.gestioninventario.dto

data class UsuarioResponse(

    val id: Long,

    val nombre: String,

    val apellido: String,

    val email: String,

    val rol: String
)