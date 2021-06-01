package com.cubetiqs.web.infrastructure.data.embeddable

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
open class Discount {
    @Column(name = "discount")
    open var discount: Double = 0.0

    @Column
    @Enumerated(EnumType.STRING)
    open var discountType: DiscountType = DiscountType.PERCENTAGE
}

enum class DiscountType {
    CASH,
    PERCENTAGE
}