package io.softwarestrategies.projectk.resource.config

import io.softwarestrategies.projectk.common.security.GrantedAuthoritiesExtractor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Value("\${application.swagger.enabled}")
    private val swaggerEnabled = false

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        var AUTH_WHITELIST: List<String> = mutableListOf( "/actuator/**" )
        val SWAGGER_WHITELIST: List<String> = mutableListOf( "/swagger-resources/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/apidocs/**" )

        if (swaggerEnabled) {
            AUTH_WHITELIST = AUTH_WHITELIST.plus(SWAGGER_WHITELIST)
        }

        http.httpBasic(Customizer.withDefaults())

        http.sessionManagement {
                sessionManagement -> sessionManagement.sessionCreationPolicy( SessionCreationPolicy.STATELESS )
        }

        val authenticationConverter = JwtAuthenticationConverter()
        authenticationConverter.setJwtGrantedAuthoritiesConverter(GrantedAuthoritiesExtractor())

        http.oauth2ResourceServer { oauth2ResourceServer ->
            oauth2ResourceServer.jwt { jwt -> jwt.jwtAuthenticationConverter(authenticationConverter) }
        }

        http
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers(*AUTH_WHITELIST.toTypedArray()).permitAll()
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    .requestMatchers("/api/v1/**").hasRole("DEFAULT")
                    .anyRequest().authenticated()
        }

        return http.build()
    }
}