package io.softwarestrategies.projectk.resource.data.repository

import io.softwarestrategies.projectk.resource.data.model.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectJpaRepository : JpaRepository<Project?, Long?> {
}