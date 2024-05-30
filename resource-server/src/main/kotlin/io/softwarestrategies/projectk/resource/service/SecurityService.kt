package io.softwarestrategies.projectk.resource.service

import org.springframework.security.access.AuthorizationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service

@Service
class SecurityService (val userService: UserService) {

    fun getUsernameFromAuthentication() : String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val principal = authentication.principal

        val CLAIM_NAME = "preferred_username"

        return when (principal) {
            is Jwt -> principal.getClaim<String>(CLAIM_NAME)
            //is DefaultOidcUser -> principal.claims[CLAIM_NAME]
            //is DefaultOAuth2AuthenticatedPrincipal -> principal.getAttribute(CLAIM_NAME)
            else -> throw AuthorizationServiceException("Unable to get principal from security context")
        }
    }

    fun getAuthenticatedUserId() : Long? {
        val username = getUsernameFromAuthentication();
        return userService.getUserIdFromUsername(username).get();
    }
}