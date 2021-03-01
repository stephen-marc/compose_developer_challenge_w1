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
