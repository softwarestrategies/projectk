package io.softwarestrategies.projectk.common.data

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ProjectResponse (
    val id: Long,
    val name: String?,
    val description: String?
)