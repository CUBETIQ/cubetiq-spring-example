package com.cubetiqs.web.infrastructure.service

import com.cubetiqs.web.infrastructure.data.Entity
import com.cubetiqs.web.infrastructure.repository.BaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull

interface BaseService <T : Entity<Long>> {
    fun getRepository(): BaseRepository<T>

    fun findAll(pageable: Pageable): Page<T> {
        return getRepository().findAll(pageable)
    }

    fun findOne(id: Long): T? {
        return getRepository().findByIdOrNull(id)
    }

    fun create(entity: T): T {
        return getRepository().save(entity)
    }

    fun createAll(entities: Collection<T>): Collection<T> {
        return getRepository().saveAll(entities)
    }

    fun delete(id: Long) {
        getRepository().deleteById(id)
    }
}