package io.softwarestrategies.projectk.ui.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Value("\${application.swagger.enabled:false}")
    private val swaggerEnabled = false

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {

        val AUTH_WHITELIST: List<String> = mutableListOf( "/actuator/**", "/", "/index.html" )
        val SWAGGER_WHITELIST: List<String> = listOf( "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**" )

        if (swaggerEnabled) {
            AUTH_WHITELIST.plus(SWAGGER_WHITELIST)
        }

        http
            .httpBasic(Customizer.withDefaults())
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .cors(Customizer.withDefaults())
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers(*AUTH_WHITELIST.toTypedArray()).permitAll()
                    .anyRequest().authenticated()
            }

        return http.build()
    }
}