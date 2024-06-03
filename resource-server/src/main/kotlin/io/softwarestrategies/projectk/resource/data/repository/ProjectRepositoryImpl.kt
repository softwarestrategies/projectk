package io.softwarestrategies.projectk.resource.data.repository

import io.softwarestrategies.projectk.resource.data.model.Project
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class ProjectRepositoryImpl @Autowired constructor(
    entityManager: EntityManager,
    private val projectJpaRepository: ProjectJpaRepository,
) : BaseRepositoryImpl<Project, Long>(Project::class.java, entityManager), ProjectRepository {

    override fun findById(id: Long) : Optional<Project?> {
        return projectJpaRepository.findById(id);
    }

    override fun findByUserId(userId: Long): List<Project> {
        val jpqlQuery = "SELECT p FROM Project p WHERE p.user.id = :userId"
        return createQuery(jpqlQuery)
            .setParameter("userId", userId)
            .resultList.filterIsInstance<Project>()
    }
}