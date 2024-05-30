package io.softwarestrategies.projectk.resource.data.model

import io.softwarestrategies.projectk.resource.data.converter.UserStatusConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import jakarta.persistence.Version
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
@Table(name = "projectk_user",
    uniqueConstraints = [UniqueConstraint(columnNames = ["username"])]
)
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Version
    var version: Int? = null

    @Column(name = "created_on")
    var createdOn: LocalDateTime = LocalDateTime.now()

    @Column(name = "modified_on")
    var modifiedOn: LocalDateTime = LocalDateTime.now()

    @Column(name = "created_by")
    @NotEmpty
    @Size(max = 255)
    var createdBy: String = ""

    @Column(name = "modified_by")
    @NotEmpty
    @Size(max = 255)
    var modifiedBy: String = ""

    @Column(name = "username")
    @NotEmpty
    @Size(max = 255)
    var username: String = ""

    @Column(name = "first_name")
    @Size(max = 255)
    var firstName: String? = null

    @Column(name = "last_name")
    @Size(max = 255)
    var lastName: String? = null

    @Column(name = "status", columnDefinition = "VARCHAR(1)")
    @Convert(converter = UserStatusConverter::class)
    var status: UserStatus = UserStatus.ACTIVE
}