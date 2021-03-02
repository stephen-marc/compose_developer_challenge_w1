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
package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.R.drawable
import com.example.androiddevchallenge.ui.drawColoredShadow
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DogImageCard(
    modifier: Modifier = Modifier,
    imageData: Any = drawable.core_img_placeholder,
    title: String,
    subtitle: String,
    byline: String,
    onClick: () -> Unit = {}
) {
  val imageDataRemember = remember { imageData }
  Card(
      modifier = modifier
        .padding(8.dp)
        .aspectRatio(1f)
        .drawColoredShadow(MaterialTheme.colors.primary)
        .clickable { onClick() },
      backgroundColor = MaterialTheme.colors.primary,
      shape = MaterialTheme.shapes.medium, elevation = 0.dp,
  ) {
    Box(contentAlignment = Alignment.BottomStart) {
      CoilImage(
          data = imageDataRemember,
          contentDescription = "",
          contentScale = ContentScale.Crop,
          fadeIn = true,
      )
      DogCardForeground(title = title, subtitle = subtitle, byline = byline)
    }
  }
}

@Composable
private fun DogCardForeground(title: String, subtitle: String, byline: String) {
  Column(
      modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.verticalGradient(
                0f to Color.Transparent,
                0.5f to Color.Transparent,
                1f to Color(0f, 0f, 0f, 0.8f)
            )
        )
        .padding(8.dp),
      verticalArrangement = Arrangement.Bottom
  ) {
    CompositionLocalProvider(LocalContentColor provides Color.White) {
      DogInfoBlock(title = title, subtitle = subtitle, byline = byline)
    }
  }
}

@Preview
@Composable
fun PreviewPetImageCard() {
  MyTheme() {
    DogImageCard(
        title = stringResource(id = R.string.internal_lorem_ipsum),
        subtitle = stringResource(id = R.string.internal_lorem_ipsum),
        byline = stringResource(id = R.string.internal_lorem_ipsum)
    )
  }
}

@Composable
fun DogInfoBlock(title: String, subtitle: String, byline: String) {
  Column(modifier = Modifier.padding(8.dp)) {
    Text(text = title, style = MaterialTheme.typography.subtitle1)
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
      Text(text = subtitle, style = MaterialTheme.typography.caption)
      Text(text = byline, style = MaterialTheme.typography.caption)
    }
  }
}
