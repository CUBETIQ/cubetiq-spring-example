package com.cubetiqs.web.infrastructure.data

import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity : Entity<Long> {
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    open var createdDate: java.util.Date? = null

    override fun setId(id: Long?) {
        this.id = id
    }

    override fun getId(): Long? {
        return id
    }

    override fun isNew(): Boolean {
        return getId() == null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as BaseEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 699169739
}