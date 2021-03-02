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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.entity.getDogByName
import com.example.androiddevchallenge.navigation.Actions
import com.example.androiddevchallenge.navigation.DetailScreen
import com.example.androiddevchallenge.navigation.MainScreen
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      val actions = remember(navController) { Actions(navController) }
      NavHost(navController = navController, startDestination = MainScreen.identifier) {
        composable(MainScreen.route()) {
          MyTheme {
            DogListScreen(onCardClick = actions.openDetails)
          }
        }
        composable(
            DetailScreen.route()
        ) { backStackEntry ->
          requireNotNull(backStackEntry.arguments?.getString(DetailScreen.dogNameArg)).let { argument ->
            MyTheme {
              DogDetailScreen(argument)
            }
          }
        }
      }
    }
  }
}

@Composable
fun DogDetailScreen(dogName: String) {
  val dog by remember { (mutableStateOf(getDogByName(dogName))) }

  ConstraintLayout(
      modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
  ) {
    val (headerImage, card, body) = createRefs()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
          .constrainAs(headerImage) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
          }
          .aspectRatio(1.3f), contentAlignment = Alignment.BottomStart
    ) {
      CoilImage(
          data = dog.imageUrl,
          contentDescription = "",
          contentScale = ContentScale.Crop,
          fadeIn = true,
      )
    }

    val barrier = createBottomBarrier(headerImage, margin = 16.dp)

    Column(
        modifier = Modifier
          .constrainAs(body) {
            top.linkTo(headerImage.bottom)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            height = Dimension.fillToConstraints
            width = Dimension.fillToConstraints
          }
          .verticalScroll(scrollState)
    ) {
      Text(text = "Textxtxtxt")
    }


    Card(
        modifier = Modifier
          .constrainAs(card) {
            top.linkTo(barrier)
            bottom.linkTo(barrier)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            height = Dimension.preferredWrapContent
            width = Dimension.fillToConstraints
          }
          .padding(16.dp)
          .sizeIn(minHeight = 80.dp),
        backgroundColor = MaterialTheme.colors.primarySurface
    ) {
      Text(text = "Textxtxtxt")
    }

  }
}

@Preview
@Composable
fun PetCellPreview() {
  MyTheme {
    DogDetailScreen("Lucy")
  }
}

//@Preview
@Composable
fun LightPreview() {
  MyTheme {
    DogListScreen()
  }
}

//@Preview
@Composable
fun DarkPreview() {
  MyTheme(darkTheme = true) {
    DogListScreen()
  }
}


