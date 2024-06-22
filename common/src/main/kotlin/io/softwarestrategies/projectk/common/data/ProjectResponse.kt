package io.softwarestrategies.projectk.common.data

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ProjectResponse (
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null
)