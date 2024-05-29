package io.softwarestrategies.projectk.resource.data.converter

import io.softwarestrategies.projectk.resource.data.model.ProjectStatus
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class ProjectStatusConverter : AttributeConverter<ProjectStatus, Char> {

    override fun convertToDatabaseColumn(attribute: ProjectStatus): Char {
        return attribute.toDBValue()
    }

    override fun convertToEntityAttribute(dbData: Char): ProjectStatus {
        return ProjectStatus.fromDBValue(dbData)
    }
}