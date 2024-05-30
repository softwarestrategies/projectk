package io.softwarestrategies.projectk.resource.data.extension

import io.softwarestrategies.projectk.common.data.ProjectResponse
import io.softwarestrategies.projectk.resource.data.model.Project

fun Project.toProjectResponse(): ProjectResponse {
    return ProjectResponse(
        id = this.id,
        name = this.name,
        description = this.description
    )
}