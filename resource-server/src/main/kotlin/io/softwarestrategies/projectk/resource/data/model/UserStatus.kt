package io.softwarestrategies.projectk.resource.data.model

enum class UserStatus(val statusCode: Char) {

    PENDING('P'),
    ACTIVE('A'),
    INACTIVE('I'),
    DELETED('D');

    companion object {
        fun fromDBValue(dbValue: Char): UserStatus = when (dbValue) {
            'P' -> PENDING
            'A' -> ACTIVE
            'I' -> INACTIVE
            'D' -> DELETED
            else -> throw IllegalArgumentException("Unknown database value: $dbValue")
        }
    }

    fun toDBValue(): Char = when (this) {
        PENDING -> 'N'
        ACTIVE -> 'A'
        INACTIVE -> 'I'
        DELETED -> 'D'
    }
}