package io.softwarestrategies.projectk.resource.config

import io.softwarestrategies.projectk.common.security.GrantedAuthoritiesExtractor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Value("\${application.swagger.enabled:false}")
    private val swaggerEnabled = false

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        val AUTH_WHITELIST: List<String> = mutableListOf( "/actuator/**" )
        val SWAGGER_WHITELIST: List<String> = listOf( "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**" )
        if (swaggerEnabled) {
            AUTH_WHITELIST.plus(SWAGGER_WHITELIST)
        }

        http
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers(*AUTH_WHITELIST.toTypedArray()).permitAll()
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }

        http.sessionManagement {
                sessionManagement -> sessionManagement.sessionCreationPolicy( SessionCreationPolicy.STATELESS )
        }

        val authenticationConverter = JwtAuthenticationConverter()
        authenticationConverter.setJwtGrantedAuthoritiesConverter(GrantedAuthoritiesExtractor())

        http
            .oauth2ResourceServer { oauth2ResourceServer ->
                oauth2ResourceServer.jwt { jwt -> jwt.jwtAuthenticationConverter(authenticationConverter) }
            }

        return http.build()
    }
}