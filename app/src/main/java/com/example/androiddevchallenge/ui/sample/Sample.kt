package com.example.androiddevchallenge.ui.sample

import com.example.androiddevchallenge.entity.AdoptableDog

class Sample {
  companion object {
    val dogs: List<AdoptableDog> = listOf(
        AdoptableDog(name = "Bailey", ageString = "6 months", sex = "male"),
        AdoptableDog(name = "Lucy", ageString = "2 years", sex = "female"),
        AdoptableDog(name = "Chloe", ageString = "18 months", sex = "female"),
        AdoptableDog(name = "Jake", ageString = "6 years", sex = "male"),
        AdoptableDog(name = "Coco", ageString = "9 year", sex = "female"),
        AdoptableDog(name = "Rocky", ageString = "3 years", sex = "male"),
        AdoptableDog(name = "Lucy", ageString = "2.5 years", sex = "female"),
    )
  }
}

