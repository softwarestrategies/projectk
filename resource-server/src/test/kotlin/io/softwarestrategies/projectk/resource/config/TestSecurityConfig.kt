package io.softwarestrategies.projectk.resource.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PropertiesLoaderUtils
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import java.util.Properties

@TestConfiguration
class TestSecurityConfig {

    @Value("\${application.users.properties.location}")
    lateinit var usersPropertiesLocation: String

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val resource: Resource = ClassPathResource(usersPropertiesLocation)
        val userProperties: Properties = PropertiesLoaderUtils.loadProperties(resource)
        return InMemoryUserDetailsManager(userProperties)
    }

    @Bean
    fun authProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService())
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }
}