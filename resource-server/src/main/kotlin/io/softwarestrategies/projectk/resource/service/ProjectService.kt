package io.softwarestrategies.projectk.resource.service

import io.softwarestrategies.projectk.common.data.ProjectResponse
import io.softwarestrategies.projectk.common.data.ProjectsResponse
import io.softwarestrategies.projectk.resource.data.repository.ProjectJpaRepository
import org.springframework.stereotype.Service

@Service
class ProjectService (
    val securityService: SecurityService,
    val projectJpaRepository: ProjectJpaRepository
) {

    fun getProjects() : ProjectsResponse {
        val username = securityService.getUsernameFromAuthentication()

        val myProject = projectJpaRepository.findAll()

        val projects : ArrayList<ProjectResponse> = arrayListOf(
            ProjectResponse(id = 1, name = "Project Name 1", description = "Description 1"),
            ProjectResponse(id = 2, name = null, description = "Description 2")
        )

        val projectsResponse = ProjectsResponse(projects)

        return projectsResponse
    }

    fun getProject() : ProjectResponse {
        val projectResponse = ProjectResponse(id = 2, name = "Project Name 2", description = "Description 2")
        return projectResponse
    }
}