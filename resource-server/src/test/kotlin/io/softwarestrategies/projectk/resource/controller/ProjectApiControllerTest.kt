package io.softwarestrategies.projectk.resource.controller

import io.softwarestrategies.projectk.common.data.ProjectResponse
import io.softwarestrategies.projectk.common.data.ProjectsResponse
import io.softwarestrategies.projectk.resource.service.ProjectService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.`when`
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ProjectApiController::class)
class ProjectApiControllerTest : BaseApiControllerTest() {

    @MockBean
    private lateinit var mockProjectService: ProjectService

    @Test
    fun `test getting all projects`() {
        val mockProjectsResponse = ProjectsResponse(
            mutableListOf(
                ProjectResponse(1, "Details1", description = "d1"),
                ProjectResponse(2, "Details2", description = "d2")
            )
        )

        `when`(mockProjectService.getProjects()).thenReturn(mockProjectsResponse)

        val resultActions = mockMvc.perform(get("/api/v1/projects")
            .with(SecurityMockMvcRequestPostProcessors.httpBasic(REGULAR_USER_USERNAME, REGULAR_USER_PASSWORD)))
            .andExpect(status().isOk)

        val returnedProjectsResponse = objectMapper.readValue(resultActions.andReturn().response.contentAsString, ProjectsResponse::class.java)

        assertEquals(returnedProjectsResponse.projects.size, 2);
        assertEquals(returnedProjectsResponse.projects.get(1).name, "Details2")
    }

    @Test
    fun `test getting a single project - successfully`() {
        val mockProjectResponse = ProjectResponse(2, "Details2", description = "d2")

        `when`(mockProjectService.getById(anyLong())).thenReturn(mockProjectResponse)

        val resultActions = mockMvc.perform(get("/api/v1/projects/1")
            .with(SecurityMockMvcRequestPostProcessors.httpBasic(REGULAR_USER_USERNAME, REGULAR_USER_PASSWORD)))
            .andExpect(status().isOk)

        val returnedProjectsResponse = objectMapper.readValue(resultActions.andReturn().response.contentAsString, ProjectsResponse::class.java)

        assertEquals(returnedProjectsResponse.projects.size, 2);
        assertEquals(returnedProjectsResponse.projects.get(1).name, "Details2")
    }
}
