package com.cubetiqs.web.infrastructure.data.domain

import com.cubetiqs.web.infrastructure.data.BaseEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.Hibernate
import javax.persistence.*

@Table(
    name = "ROLES", indexes = [
        Index(name = "IDX_ROLE_ID_NAME", columnList = "ID, NAME")
    ]
)
@Entity
open class Role : BaseEntity() {
    @Column(name = "NAME", length = 100, unique = true, nullable = false)
    open var name: String? = null

    @JoinTable(
        name = "ROLE_PRIVILEGE_FK",
        joinColumns = [JoinColumn(name = "ROLE_ID")],
        inverseJoinColumns = [JoinColumn(name = "PRIVILEGE_ID")]
    )
    @ManyToMany(
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH],
        fetch = FetchType.LAZY,
    )
    @JsonManagedReference
    open var privileges: MutableCollection<Privilege> = mutableSetOf()

    @ManyToMany(
        mappedBy = "roles",
        cascade = [CascadeType.DETACH, CascadeType.REFRESH],
        fetch = FetchType.LAZY,
    )
    @JsonBackReference
    open var users: MutableCollection<User> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Role

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1179619963

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , createdDate = $createdDate , name = $name )"
    }
}