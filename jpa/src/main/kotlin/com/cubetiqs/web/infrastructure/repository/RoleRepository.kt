package com.cubetiqs.web.infrastructure.repository;

import com.cubetiqs.web.infrastructure.data.domain.Role
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : BaseRepository<Role> {
    @Query("select r from Role r where upper(r.name) = upper(?1)")
    fun findByNameIsIgnoreCase(name: String): Optional<Role>
}