package io.softwarestrategies.projectk.resource.data.model

import io.softwarestrategies.projectk.resource.data.converter.ProjectStatusConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.Version
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
@Table(name = "project")
class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null

    @Column(name = "name")
    @NotEmpty
    @Size(max = 50)
    var name: String = ""

    @Column(name = "description")
    @NotEmpty
    @Size(max = 255)
    var description: String = ""

    @Column(name = "status", columnDefinition = "VARCHAR(1)")
    @Convert(converter = ProjectStatusConverter::class)
    var status: ProjectStatus = ProjectStatus.ACTIVE
}