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
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells.Fixed
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R.string
import com.example.androiddevchallenge.entity.AdoptableDog
import com.example.androiddevchallenge.entity.BASE_URL_DOG_IMAGES
import com.example.androiddevchallenge.ui.components.PetCard
import com.example.androiddevchallenge.ui.sample.Sample
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyTheme {
        DogListScreen()
      }
    }
  }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun DogListScreen() {
  val dogsToDisplay = Sample.dogs

  Scaffold {
    Surface(color = MaterialTheme.colors.background) {
      val gridState = rememberLazyListState()
      val alpha = listScrollStateToAlpha(gridState)
      var height by remember { mutableStateOf(Dp.Unspecified) }

      MainHeader(
          modifier = Modifier
            .onGloballyPositioned {
              height = with(LocalDensity.current) { it.size.height / density }.dp
            }
            .alpha(alpha)
      )

      AnimatedVisibility(
          visible = height != Dp.Unspecified, enter = fadeIn(), initiallyVisible = false
      ) {
        PetCardGrid(gridState, height, dogsToDisplay)
      }
    }
  }
}

@Composable
private fun listScrollStateToAlpha(gridState: LazyListState) =
  remember(gridState.firstVisibleItemScrollOffset, gridState.firstVisibleItemIndex) {
    if (gridState.firstVisibleItemIndex > 0) {
      0f
    } else {
      1f - (gridState.firstVisibleItemScrollOffset / 100f) / 2
    }
  }

@Composable
private fun MainHeader(
    modifier: Modifier = Modifier
) {
  Surface(
      modifier = modifier,
      color = MaterialTheme.colors.primary
  ) {
    Column(
        modifier
          .fillMaxWidth()
          .padding(start = 8.dp)
    ) {
      Spacer(modifier = Modifier.height(8.dp))
      Text(
          text = stringResource(string.mainlist_app_title), style = MaterialTheme.typography.h4,
      )
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = stringResource(string.main_list_app_subtitle),
            style = MaterialTheme.typography.subtitle2,
        )
      }
      Spacer(modifier = Modifier.height(8.dp))
    }
  }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
private fun PetCardGrid(
    gridState: LazyListState,
    firstRowOffset: Dp,
    items: List<AdoptableDog>
) {
  LazyVerticalGrid(state = gridState, cells = Fixed(2)) {
    items(count = items.size) { index ->
      val dog = items[index]
      val modifier = if (index < 2) {
        Modifier.padding(top = firstRowOffset + 8.dp)
      } else {
        Modifier
      }
      PetCard(
          modifier = modifier,
          imageData = "$BASE_URL_DOG_IMAGES${dog.id}",
          title = dog.name,
          subtitle = "${dog.ageString} - ${dog.sex}"
      )
    }
  }
}

@Preview
@Composable
fun PetCellPreview() {
  MyTheme {
    PetCard()
  }
}

@Preview
@Composable
fun LightPreview() {
  MyTheme {
    DogListScreen()
  }
}

@Preview
@Composable
fun DarkPreview() {
  MyTheme(darkTheme = true) {
    DogListScreen()
  }
}
