package com.cubetiqs.web.infrastructure.data.domain

import com.cubetiqs.web.infrastructure.data.BaseEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.Hibernate
import javax.persistence.*

@Table(
    name = "PRIVILEGES", indexes = [
        Index(name = "IDX_PRIVILEGE_ID_NAME", columnList = "ID, NAME")
    ]
)
@Entity
open class Privilege : BaseEntity() {
    @Column(name = "NAME", unique = true, length = 100, nullable = false)
    open var name: String? = null

    @ManyToMany(
        mappedBy = "privileges",
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH],
        fetch = FetchType.LAZY,
    )
    @JsonBackReference
    open var roles: MutableCollection<Role> = mutableListOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Privilege

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 2090507994

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , createdDate = $createdDate , name = $name )"
    }
}