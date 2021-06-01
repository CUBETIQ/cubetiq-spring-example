package com.cubetiqs.web.infrastructure.data.domain

import com.cubetiqs.web.infrastructure.data.BaseEntity
import com.cubetiqs.web.infrastructure.data.embeddable.Discount
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.Hibernate
import javax.persistence.*

@Table(name = "PROJECTS")
@Entity
open class Project : BaseEntity() {
    @Column(name = "NAME", length = 150)
    open var name: String? = null

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT",)
    open var description: String? = null

    @JsonManagedReference
    @JoinColumn(name = "USER_ID")
    @ManyToOne(cascade = [CascadeType.DETACH, CascadeType.REFRESH], fetch = FetchType.LAZY)
    open var user: User? = null

    @Embedded
    open var discount: Discount? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Project

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1545761250

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , createdDate = $createdDate , name = $name , description = $description )"
    }
}