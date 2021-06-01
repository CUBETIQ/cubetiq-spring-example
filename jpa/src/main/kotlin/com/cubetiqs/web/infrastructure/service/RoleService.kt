package com.cubetiqs.web.infrastructure.service

import com.cubetiqs.web.infrastructure.data.domain.Role
import com.cubetiqs.web.infrastructure.repository.BaseRepository
import com.cubetiqs.web.infrastructure.repository.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoleService @Autowired constructor(
    private val roleRepository: RoleRepository,
) : BaseService<Role> {
    override fun getRepository(): BaseRepository<Role> {
        return roleRepository
    }

    @Transactional
    fun findOrCreate(role: Role): Role {
        val existed = roleRepository.findByNameIsIgnoreCase(role.name ?: "")
        if (existed.isPresent) {
            return existed.get()
        }

        return create(role)
    }
}