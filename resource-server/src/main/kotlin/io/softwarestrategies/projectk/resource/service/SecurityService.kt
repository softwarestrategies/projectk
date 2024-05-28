package io.softwarestrategies.projectk.resource.service

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service

@Service
class SecurityService {

    /**
     * TODO Eventually, this will return a ProjectUser object with the user's id.  This will be used for object-level security
     */
    fun getUsernameFromAuthentication() : Any? {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val principal = authentication.principal

        val CLAIM_NAME = "preferred_username"

        return when (principal) {
            is Jwt -> principal.getClaim<String>(CLAIM_NAME)
            is DefaultOidcUser -> principal.claims[CLAIM_NAME]
            is DefaultOAuth2AuthenticatedPrincipal -> principal.getAttribute(CLAIM_NAME)
            else -> null
        }
    }
}