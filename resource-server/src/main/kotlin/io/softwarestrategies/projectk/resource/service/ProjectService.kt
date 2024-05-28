package io.softwarestrategies.projectk.resource.service

import io.softwarestrategies.projectk.common.data.ProjectResponse
import io.softwarestrategies.projectk.common.data.ProjectsResponse
import org.springframework.stereotype.Service

@Service
class ProjectService (
    val securityService: SecurityService
) {

    fun getProjects() : ProjectsResponse {
        val username = securityService.getUsernameFromAuthentication()

        val projectResponse = ProjectResponse(id = 1, name = "Project Name 1", description = "Description 1")
        val projects : ArrayList<ProjectResponse> = arrayListOf(projectResponse)
        val projectsResponse = ProjectsResponse(projects)
        return projectsResponse
    }

    fun getProject() : ProjectResponse {
        val projectResponse = ProjectResponse(id = 2, name = "Project Name 2", description = "Description 2")
        return projectResponse
    }
}