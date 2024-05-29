package io.softwarestrategies.projectk.resource.data.repository

import io.softwarestrategies.projectk.resource.data.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User?, Long?>