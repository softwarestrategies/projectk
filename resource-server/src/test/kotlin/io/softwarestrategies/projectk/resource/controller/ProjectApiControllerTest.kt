package io.softwarestrategies.projectk.resource.controller

import io.softwarestrategies.projectk.common.data.ProjectResponse
import io.softwarestrategies.projectk.common.data.ProjectsResponse
import io.softwarestrategies.projectk.resource.exception.EntityNotFoundException
import io.softwarestrategies.projectk.resource.service.ProjectService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.`when`
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ProjectApiController::class)
class ProjectApiControllerTest : BaseApiControllerTest() {

    @MockBean
    private lateinit var mockProjectService: ProjectService

    @Test
    @WithAnonymousUser
    fun `test that authentication is working - failure with a non-existing user`() {
        mockMvc.perform(get("/api/v1/projects/11111")).andExpect(status().isUnauthorized)
    }

    @Test
    @WithMockUser(roles = ["NOT_APPLICABLE_ROLE"])
    fun `test that authorization is working - failure with an existing user not having the necessary role`() {
        mockMvc.perform(get("/api/v1/projects/11111")).andExpect(status().isForbidden)
    }

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
            .with(SecurityMockMvcRequestPostProcessors.httpBasic(DEFAULT_USER_USERNAME, DEFAULT_USER_PASSWORD)))
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
            .with(SecurityMockMvcRequestPostProcessors.httpBasic(DEFAULT_USER_USERNAME, DEFAULT_USER_PASSWORD)))
            .andExpect(status().isOk)
        val returnedProjectResponse = objectMapper.readValue(resultActions.andReturn().response.contentAsString, ProjectResponse::class.java)
        assertEquals(returnedProjectResponse.description, "d2");
    }

    @Test
    fun `test getting a single project - unsuccessfully`() {
        `when`(mockProjectService.getById(anyLong())).thenThrow(EntityNotFoundException::class.java)

        mockMvc.perform(get("/api/v1/projects/11111")
            .with(SecurityMockMvcRequestPostProcessors.httpBasic(DEFAULT_USER_USERNAME, DEFAULT_USER_PASSWORD)))
            .andExpect(status().is4xxClientError)
    }
}
