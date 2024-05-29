package io.softwarestrategies.projectk.resource.data.converter

import io.softwarestrategies.projectk.resource.data.model.UserStatus
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class UserStatusConverter : AttributeConverter<UserStatus, Char> {

    override fun convertToDatabaseColumn(attribute: UserStatus): Char {
        return attribute.toDBValue()
    }

    override fun convertToEntityAttribute(dbData: Char): UserStatus {
        return UserStatus.fromDBValue(dbData)
    }
}