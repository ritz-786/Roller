package com.example.packaginglibrary.domain

data class House(
    val id: Int,
    val location: LocatedAt,
    val city: String,
    val state: String
)