package io.softwarestrategies.projectk.resource.data.repository

import io.softwarestrategies.projectk.resource.data.model.Project
import java.util.Optional

interface ProjectRepository {

    fun findById(id: Long): Optional<Project?>
    fun findByUserId(userId: Long): List<Project>
}