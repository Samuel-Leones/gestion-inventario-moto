package co.edu.unibarranquilla.gestioninventario.service

import co.edu.unibarranquilla.gestioninventario.dto.LoginRequest
import co.edu.unibarranquilla.gestioninventario.dto.LoginResponse
import co.edu.unibarranquilla.gestioninventario.model.User
import co.edu.unibarranquilla.gestioninventario.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {


    fun login(request: LoginRequest): LoginResponse {

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )


        val usuario = userRepository.findByEmail(request.email)
            ?: throw RuntimeException("Usuario no encontrado")


        val token = jwtService.generateToken(usuario.email)


        return LoginResponse(
            token = token
        )
    }


    fun register(user: User): User {

        if (userRepository.existsByEmail(user.email)) {
            throw RuntimeException("El correo ya está registrado")
        }


        user.password = passwordEncoder.encode(user.password)


        return userRepository.save(user)
    }

}