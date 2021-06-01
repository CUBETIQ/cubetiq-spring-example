package com.cubetiqs.web.controller

import com.cubetiqs.web.infrastructure.data.domain.Role
import com.cubetiqs.web.infrastructure.data.domain.User
import com.cubetiqs.web.infrastructure.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/users"])
class UserController @Autowired constructor(
    private val userService: UserService,
) {
    @GetMapping
    fun getAll(
        pageable: Pageable,
    ): ResponseEntity<Any> {
        val start = System.currentTimeMillis()
        val response = userService.findAll(Pageable.unpaged()).content
        val end = System.currentTimeMillis() - start

        return ResponseEntity.ok(
            mapOf(
                //"data" to response,
                "records" to response.size,
                "time" to end,
            )
        )
    }

    @GetMapping("/search")
    fun search(
        pageable: Pageable,
        @RequestParam(value = "roles", defaultValue = "[]") roles: Collection<String>
    ): ResponseEntity<Any> {
        val start = System.currentTimeMillis()
        val response = userService.findAllByRoles(roles)
        val end = System.currentTimeMillis() - start

        return ResponseEntity.ok(
            mapOf(
                //"data" to response,
                "records" to response.size,
                "time" to end,
            )
        )
    }

    @GetMapping("/{id}")
    fun getOne(
        @PathVariable id: Long,
    ): ResponseEntity<Any?> {
        return ResponseEntity.ok(userService.findOne(id))
    }

    @PostMapping
    fun create(): ResponseEntity<Any> {
        val start = System.currentTimeMillis()

        val users = IntRange(1, 5000).map {
            User()
                .apply {
                    this.name = "A-$it"
                    this.password = it.toString()
                    this.username = "A$it"
                    this.roles.addAll(
                        setOf(
                            Role().apply { this.name = "ADMIN" },
                            Role().apply { this.name = "SUPER_ADMIN" },
                            Role().apply { this.name = "USER" },
                        )
                    )
                }
        }

        val records = userService.createAll(users)

        val end = System.currentTimeMillis() - start

        return ResponseEntity.ok(
            mapOf(
                "time" to end,
                "records" to records.size,
            )
        )
    }
}