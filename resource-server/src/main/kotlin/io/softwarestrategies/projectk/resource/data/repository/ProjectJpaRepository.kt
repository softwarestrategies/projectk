package io.softwarestrategies.projectk.resource.data.repository

import io.softwarestrategies.projectk.resource.data.model.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProjectJpaRepository : JpaRepository<Project?, Long?> {

    @Query("SELECT p FROM Project p WHERE p.user.id = :userId")
    fun findByUserId(userId: Long): List<Project>
}