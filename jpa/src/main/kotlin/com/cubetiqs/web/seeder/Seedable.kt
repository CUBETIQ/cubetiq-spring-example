package com.cubetiqs.web.seeder

fun interface Seedable {
    fun seed(): Any?
}