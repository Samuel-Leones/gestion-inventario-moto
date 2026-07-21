package co.edu.unibarranquilla.gestioninventario.repository

import co.edu.unibarranquilla.gestioninventario.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean
}