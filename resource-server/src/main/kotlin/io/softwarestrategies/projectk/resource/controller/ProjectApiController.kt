package io.softwarestrategies.projectk.resource.controller

import io.softwarestrategies.projectk.common.data.ProjectResponse
import io.softwarestrategies.projectk.common.data.ProjectsResponse
import io.softwarestrategies.projectk.resource.service.ProjectService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/projects")
@Tag(
    name = "CRUD REST APIs for User Resource",
    description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
class ProjectApiController(
    val projectService: ProjectService) {

    @GetMapping
    fun getProjects(): ResponseEntity<ProjectsResponse> {
        val response: ProjectsResponse? = projectService.getProjects()
        return ResponseEntity<ProjectsResponse>(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getProject(@PathVariable id: Long): ResponseEntity<ProjectResponse> {
        val response: ProjectResponse? = projectService.getById(id)
        return ResponseEntity<ProjectResponse>(response, HttpStatus.OK)
    }
}