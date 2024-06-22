package io.softwarestrategies.projectk.resource.controller

import io.softwarestrategies.projectk.common.data.ProjectResponse
import io.softwarestrategies.projectk.common.data.ProjectsResponse
import io.softwarestrategies.projectk.resource.service.ProjectService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
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
    fun `should return OK when getting projects`() {
        val mockProjectsResponse = ProjectsResponse(
            mutableListOf(
                ProjectResponse(1, "Details1", description = "d1"),
                ProjectResponse(2, "Details2", description = "d2")
            )
        )

        `when`(mockProjectService.getProjects()).thenReturn(mockProjectsResponse)

        val VALID_USERNAME = "test-regular-user"
        val VALID_PASSWORD = "5D4afcK5j@4M9cDP"

        val resultActions = mockMvc.perform(get("/api/v1/projects")
            .with(SecurityMockMvcRequestPostProcessors.httpBasic(VALID_USERNAME, VALID_PASSWORD)))
            .andExpect(status().isOk)

        val returnedProjectsResponse = objectMapper.readValue(resultActions.andReturn().response.contentAsString, ProjectsResponse::class.java)

        assertEquals(returnedProjectsResponse.projects.size, 2);
        assertEquals(returnedProjectsResponse.projects.get(1).name, "Details2")
    }
}
