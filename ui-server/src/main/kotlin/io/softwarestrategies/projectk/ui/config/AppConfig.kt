package io.softwarestrategies.projectk.ui.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository
import org.springframework.web.client.RestClient

@Configuration
class AppConfig {

    @org.springframework.context.annotation.Bean
    fun restClient(
        clientRegistrationRepository: ClientRegistrationRepository?,
        authorizedClientRepository: OAuth2AuthorizedClientRepository?
    ): RestClient {
        return RestClient.builder()
            .build()
    }
}