package com.cubetiqs.web.infrastructure.repository

import com.cubetiqs.web.infrastructure.data.domain.Project
import com.cubetiqs.web.infrastructure.projection.ProjectInfo
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : BaseRepository<Project> {
    @Query("select p from Project p where p.name like ?1%")
    fun findAllByNameStartsWith(startsWith: String): Collection<ProjectInfo>

    fun findAllByUserUsername(username: String): Collection<Project>
}