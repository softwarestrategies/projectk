package io.softwarestrategies.projectk.resource.service

import io.softwarestrategies.projectk.resource.data.repository.UserJpaRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService (
    val userJpaRepository: UserJpaRepository
) {

    fun getUserIdFromUsername(username : String) : Optional<Long> {
        return userJpaRepository.getUserIdFromUsername(username);
    }
}