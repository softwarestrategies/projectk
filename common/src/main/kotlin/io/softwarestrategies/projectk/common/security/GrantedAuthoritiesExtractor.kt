package io.softwarestrategies.projectk.common.security

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import java.util.*

class GrantedAuthoritiesExtractor : Converter<Jwt, Collection<GrantedAuthority>> {

    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val claims = jwt.claims["realm_access"] as? Map<*, *>
        val roles = claims?.get("roles") as? List<*>
        return roles
            ?.filterIsInstance<String>()
            ?.map { SimpleGrantedAuthority("ROLE_${it.uppercase(Locale.getDefault())}") }
            ?: emptyList()
    }
}