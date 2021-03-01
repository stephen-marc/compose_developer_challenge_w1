package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R.drawable
import com.example.androiddevchallenge.ui.drawColoredShadow
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PetCard(
    modifier: Modifier = Modifier,
    imageData: Any = drawable.core_img_placeholder,
    title: String = "Lorem Ipsum",
    subtitle: String = "Lorem Ipsum",
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
      PetInfoCardForeground(title, subtitle)
    }
  }
}

@Composable
private fun PetInfoCardForeground(title: String, subtitle: String) {
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
    PetCardInfo(title, subtitle)
  }
}

@Composable
private fun PetCardInfo(title: String, subtitle: String) {
  CompositionLocalProvider(LocalContentColor provides Color.White) {
    Text(text = title, style = MaterialTheme.typography.subtitle1)
    Row(verticalAlignment = Alignment.CenterVertically) {
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Icon(
            painter = painterResource(drawable.ic_pets_24),
            contentDescription = "Pets Icon",
            modifier = Modifier
              .size(24.dp)
              .padding(2.dp)
        )
        Text(text = subtitle, style = MaterialTheme.typography.caption)
      }
    }

  }
}
