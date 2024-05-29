package io.softwarestrategies.projectk.resource.data.repository

import io.softwarestrategies.projectk.resource.data.model.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface ProjectJpaRepository : JpaRepository<Project?, Long?> {

    @Query("SELECT p FROM Project p")
    fun findByUserId(): Optional<List<Project?>>?

}