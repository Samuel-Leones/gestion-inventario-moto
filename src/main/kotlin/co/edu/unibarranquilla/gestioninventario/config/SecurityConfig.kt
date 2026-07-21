package co.edu.unibarranquilla.gestioninventario.config

import co.edu.unibarranquilla.gestioninventario.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {


    @Bean
    fun securityFilterChain(
        http: HttpSecurity
    ): SecurityFilterChain {


        return http
            .csrf {
                it.disable()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {

                it.requestMatchers(
                    "/auth/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/api-docs/**"
                ).permitAll()

                it.anyRequest().authenticated()

            }
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )
            .build()
    }


    @Bean
    fun authenticationManager(
        configuration: AuthenticationConfiguration
    ): AuthenticationManager {

        return configuration.authenticationManager
    }

}