/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.sample

import com.example.androiddevchallenge.entity.AdoptableDog

class Sample {
  companion object {
    val dogs: List<AdoptableDog> = listOf(
        AdoptableDog(
            name = "Bailey",
            ageString = "6 months",
            breed = "Silky Terrier",
            sex = "male",
            imageUrl = "https://images.dog.ceo/breeds/terrier-silky/n02097658_3693.jpg"
        ),
        AdoptableDog(
            name = "Lucy",
            ageString = "2 years",
            sex = "female",
            breed = "Eskimo",
            imageUrl = "https://images.dog.ceo/breeds/eskimo/n02109961_18527.jpg"
        ),
        AdoptableDog(
            name = "Chloe",
            ageString = "18 months",
            sex = "female",
            breed = "Norwegian Elkhound",
            imageUrl = "https://images.dog.ceo/breeds/elkhound-norwegian/n02091467_7407.jpg"
        ),
        AdoptableDog(
            name = "Jake",
            ageString = "6 years",
            sex = "male",
            breed = "Pomeranian",
            imageUrl = "https://images.dog.ceo/breeds/pomeranian/n02112018_5317.jpg"
        ),
        AdoptableDog(
            name = "Coco",
            ageString = "9 year",
            sex = "female",
            breed = "Samoyed",
            imageUrl = "https://images.dog.ceo/breeds/samoyed/n02111889_6512.jpg"
        ),
        AdoptableDog(
            name = "Rocky",
            ageString = "3 years",
            sex = "male",
            breed = "Vizsla",
            imageUrl = "https://images.dog.ceo/breeds/vizsla/n02100583_4439.jpg"
        ),
        AdoptableDog(
            name = "Maggie",
            ageString = "2.5 years",
            sex = "female",
            breed = "Welsh Spaniel",
            imageUrl = "https://images.dog.ceo/breeds/spaniel-welsh/n02102177_1999.jpg"
        ),
    )
  }
}
