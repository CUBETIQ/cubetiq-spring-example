package com.cubetiqs.web.infrastructure.data.domain

import com.cubetiqs.web.infrastructure.data.BaseEntity
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.Hibernate
import javax.persistence.*

@Table(
    name = "USERS", indexes = [
        Index(name = "IDX_USER_ID_USERNAME", columnList = "ID, USERNAME")
    ]
)
@Entity
open class User : BaseEntity() {
    @Column(name = "USERNAME", unique = true, length = 50)
    open var username: String? = null

    @Column(name = "NAME")
    open var name: String? = null

    @Column(name = "PASSWORD", length = 150)
    open var password: String? = null

    @JoinTable(
        name = "USER_ROLE_FK",
        joinColumns = [JoinColumn(name = "USER_ID")],
        inverseJoinColumns = [JoinColumn(name = "ROLE_ID")]
    )
    @ManyToMany(
        cascade = [CascadeType.REFRESH, CascadeType.DETACH],
        fetch = FetchType.LAZY,
    )
    @JsonManagedReference
    open var roles: MutableCollection<Role> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 562048007

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , createdDate = $createdDate , username = $username , name = $name , password = $password )"
    }
}