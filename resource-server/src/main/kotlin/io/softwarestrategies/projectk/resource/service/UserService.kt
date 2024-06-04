package io.softwarestrategies.projectk.resource.service

import io.softwarestrategies.projectk.resource.data.repository.UserJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
@Transactional(readOnly = true)
class UserService (
    val userJpaRepository: UserJpaRepository
) {

    fun getUserIdFromUsername(username : String) : Optional<Long> {
        return userJpaRepository.getUserIdFromUsername(username);
    }
}