package io.softwarestrategies.projectk.resource.service

import io.softwarestrategies.projectk.common.data.ProjectResponse
import io.softwarestrategies.projectk.common.data.ProjectsResponse
import io.softwarestrategies.projectk.resource.data.extension.toProjectResponse
import io.softwarestrategies.projectk.resource.data.repository.ProjectRepository
import io.softwarestrategies.projectk.resource.exception.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

import org.springframework.security.access.AccessDeniedException
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProjectService (
    val securityService: SecurityService,
    val projectRepository: ProjectRepository
) {

    fun getProjects() : ProjectsResponse? {
        val authenticatedUserId = securityService.getAuthenticatedUserId()
        val projects = authenticatedUserId?.let { projectRepository.findByUserId(it) }
        val projectResponses = projects?.stream()?.map { it.toProjectResponse() }?.collect(Collectors.toList())
        return projectResponses?.let { ProjectsResponse(it) }
    }

    fun getById(id: Long) : ProjectResponse? {
        val authenticatedUserId = securityService.getAuthenticatedUserId()
        val project = projectRepository.findById(id).orElseThrow { EntityNotFoundException("Project not found: " + id) }

        if (project?.user?.id != authenticatedUserId) {
            throw AccessDeniedException("User denied access to Project: " + id)
        }

        return project?.toProjectResponse()
    }
}