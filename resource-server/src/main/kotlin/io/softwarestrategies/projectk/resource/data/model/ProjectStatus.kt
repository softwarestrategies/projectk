package io.softwarestrategies.projectk.resource.data.model

enum class ProjectStatus(val statusCode: Char) {

    ACTIVE('A'),
    INACTIVE('I'),
    DELETED('D'),
    CLOSED('C');

    companion object {
        fun fromDBValue(dbValue: Char): ProjectStatus = when (dbValue) {
            'A' -> ACTIVE
            'I' -> INACTIVE
            'D' -> DELETED
            'C' -> CLOSED
            else -> throw IllegalArgumentException("Unknown database value: $dbValue")
        }
    }

    fun toDBValue(): Char = when (this) {
        ACTIVE -> 'A'
        INACTIVE -> 'I'
        DELETED -> 'D'
        CLOSED -> 'C'
    }
}