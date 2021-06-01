package com.cubetiqs.web.infrastructure.repository

import com.cubetiqs.web.infrastructure.data.Entity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepository<T : Entity<Long>> : JpaRepository<T, Long>