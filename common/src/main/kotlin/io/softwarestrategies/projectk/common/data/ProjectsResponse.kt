package io.softwarestrategies.projectk.common.data

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ProjectsResponse(val projects: List<ProjectResponse>)