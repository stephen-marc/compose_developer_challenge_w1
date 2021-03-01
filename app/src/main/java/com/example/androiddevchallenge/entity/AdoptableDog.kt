package com.example.androiddevchallenge.entity

import java.util.UUID

data class AdoptableDog(
    val id: String = UUID
      .randomUUID()
      .toString(),
    val name: String,
    val ageString: String,
    val sex: String
)

const val BASE_URL_DOG_IMAGES = "https://placedog.net/1000?random&"
