package co.edu.unibarranquilla.gestioninventario.service

import co.edu.unibarranquilla.gestioninventario.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service


@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {


    override fun loadUserByUsername(email: String): UserDetails {


        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException(
                "Usuario no encontrado"
            )


        return org.springframework.security.core.userdetails.User
            .builder()
            .username(user.email)
            .password(user.password)
            .authorities(
                SimpleGrantedAuthority(
                    "ROLE_${user.role.name}"
                )
            )
            .disabled(!user.active)
            .build()
    }

}
