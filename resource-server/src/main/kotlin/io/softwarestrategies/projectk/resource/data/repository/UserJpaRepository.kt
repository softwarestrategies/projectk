package io.softwarestrategies.projectk.resource.data.repository

import io.softwarestrategies.projectk.resource.data.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface UserJpaRepository : JpaRepository<User?, Long?> {

    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    fun getUserIdFromUsername(username: String): Optional<Long>
}