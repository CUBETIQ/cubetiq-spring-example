package com.cubetiqs.web.infrastructure.service

import com.cubetiqs.web.infrastructure.data.domain.User
import com.cubetiqs.web.infrastructure.projection.UserInfo
import com.cubetiqs.web.infrastructure.repository.BaseRepository
import com.cubetiqs.web.infrastructure.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val roleService: RoleService,
) : BaseService<User> {
    override fun getRepository(): BaseRepository<User> {
        return userRepository
    }

    fun findAllByRoles(roles: Collection<String>): Collection<UserInfo> {
        return userRepository.findAllByRoles(roles)
    }

    @Transactional
    override fun createAll(entities: Collection<User>): Collection<User> {
        entities.forEach { user ->
            val roles = user.roles.map { role ->
                roleService.findOrCreate(role)
            }
            user.roles.clear()
            user.roles.addAll(roles)
        }
        return super.createAll(entities)
    }
}