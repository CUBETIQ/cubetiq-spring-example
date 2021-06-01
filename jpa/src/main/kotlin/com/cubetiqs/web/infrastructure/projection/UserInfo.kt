package com.cubetiqs.web.infrastructure.projection

interface UserInfo {
    val id: Long?
    val name: String?
    val username: String?
    val roles: MutableCollection<RoleInfo>

    interface RoleInfo {
        val id: Long?
        val name: String?
    }
}