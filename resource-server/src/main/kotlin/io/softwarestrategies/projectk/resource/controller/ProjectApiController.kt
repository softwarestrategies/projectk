package io.softwarestrategies.projectk.resource.controller

import io.softwarestrategies.projectk.common.data.ProjectResponse
import io.softwarestrategies.projectk.common.data.ProjectsResponse
import io.softwarestrategies.projectk.resource.service.ProjectService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/projects")
class ProjectApiController(
    val projectService: ProjectService) {

    @GetMapping
    fun getProjects(): ResponseEntity<ProjectsResponse> {
        val response: ProjectsResponse = projectService.getProjects()
        return ResponseEntity<ProjectsResponse>(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getProject(): ResponseEntity<ProjectResponse> {
        val response: ProjectResponse = projectService.getProject()
        return ResponseEntity<ProjectResponse>(response, HttpStatus.OK)
    }
}