package com.cubetiqs.web.infrastructure.service

import com.cubetiqs.web.infrastructure.data.domain.Project
import com.cubetiqs.web.infrastructure.projection.ProjectInfo
import com.cubetiqs.web.infrastructure.repository.BaseRepository
import com.cubetiqs.web.infrastructure.repository.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService @Autowired constructor(
    private val projectRepository: ProjectRepository,
) : BaseService<Project> {
    override fun getRepository(): BaseRepository<Project> {
        return projectRepository
    }

    override fun createAll(entities: Collection<Project>): Collection<Project> {
        return super.createAll(entities)
    }

    fun findAllStartsWith(startsWith: String): Collection<ProjectInfo> {
        return projectRepository.findAllByNameStartsWith(startsWith)
    }
}