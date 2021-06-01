package com.cubetiqs.web.infrastructure.repository;

import com.cubetiqs.web.infrastructure.data.domain.User
import com.cubetiqs.web.infrastructure.projection.UserInfo
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : BaseRepository<User> {
    @Query("select u from User u left join u.roles roles where roles.name in ?1")
    fun findAllByRoles(names: Collection<String>): Collection<UserInfo>
}