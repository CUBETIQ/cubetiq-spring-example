package com.cubetiqs.web.controller

import com.cubetiqs.web.infrastructure.data.domain.Project
import com.cubetiqs.web.infrastructure.data.domain.User
import com.cubetiqs.web.infrastructure.service.ProjectService
import com.cubetiqs.web.infrastructure.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/projects"])
class ProjectController @Autowired constructor(
    private val projectService: ProjectService,
    private val userService: UserService,
) {
    @GetMapping
    fun getAll(
        pageable: Pageable,
        @RequestParam(value = "name", defaultValue = "") name: String
    ): ResponseEntity<Any> {
        val response = if (name.isNotEmpty()) {
            projectService.findAllStartsWith(name)
        } else {
            projectService.findAll(Pageable.unpaged()).content
        }

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getOne(
        @PathVariable id: Long,
    ): ResponseEntity<Any?> {
        return ResponseEntity.ok(projectService.findOne(id))
    }

    @PostMapping
    fun create(): ResponseEntity<Any> {
        val user = userService.create(
            User().apply {
                this.name = "CUBETIQ"
                this.username = "cubetiq"
            }
        )

        val projects = IntRange(1, 50).map {
            Project().apply {
                this.name = "A-$it"
                this.description = "This is a description of $it"
                this.user = user
            }
        }

        projectService.createAll(projects)

        return ResponseEntity.ok(projects)
    }
}