package io.softwarestrategies.projectk.resource.data.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.TypedQuery
import org.hibernate.Session
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable


@Transactional
abstract class BaseRepositoryImpl<T, ID : Serializable>(
    domainClass: Class<T>,
    @PersistenceContext
    private val entityManager: EntityManager
) : SimpleJpaRepository<T, ID>(domainClass, entityManager) {

    protected fun getCurrentSession(): Session {
        return entityManager.unwrap(Session::class.java)
    }

    protected fun createQuery(jpqlQueryToExecute: String): TypedQuery<T> {
        return entityManager.createQuery(jpqlQueryToExecute, getDomainClass())
    }
}