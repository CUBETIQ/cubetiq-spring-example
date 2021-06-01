package com.cubetiqs.web.infrastructure.data

import org.springframework.data.domain.Persistable
import java.io.Serializable

interface Entity <ID : Serializable> : Serializable, Persistable<ID> {
    fun setId(id: ID?)
}