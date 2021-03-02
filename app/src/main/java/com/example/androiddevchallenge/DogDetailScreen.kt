package com.example.androiddevchallenge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.R.drawable
import com.example.androiddevchallenge.R.string
import com.example.androiddevchallenge.entity.getDogByName
import com.example.androiddevchallenge.ui.components.DogInfoCard
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DogDetailScreen(dogName: String, onBackPressed: () -> Unit = { /*TODO*/ }) {
  val dog by remember { (mutableStateOf(getDogByName(dogName))) }

  Scaffold(topBar = {
    TopAppBar(title = {
      Text(text = "Dog Details")
    }, navigationIcon = {
      IconButton(onClick = onBackPressed) {
        Icon(
            painter = painterResource(id = drawable.ic_baseline_arrow_back_24),
            contentDescription = "Up navigation"
        )
      }
    })
  }) {
    ConstraintLayout(
        modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth()
    ) {
      val (headerImage, card, body) = createRefs()
      
      HeaderImage(
          modifier = Modifier
            .constrainAs(headerImage) {
              top.linkTo(parent.top)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
            },
          imageUrl = dog.imageUrl
      )

      val barrier = createBottomBarrier(headerImage, margin = 16.dp)

      StoryContentBox(
          modifier = Modifier
            .constrainAs(body) {
              top.linkTo(headerImage.bottom)
              bottom.linkTo(parent.bottom)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
              height = Dimension.fillToConstraints
              width = Dimension.fillToConstraints
            },
          dogName = dogName
      )

      DogInfoCard(
          modifier = Modifier
            .constrainAs(card) {
              top.linkTo(barrier)
              bottom.linkTo(barrier)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
              height = Dimension.preferredWrapContent
              width = Dimension.fillToConstraints
            }
            .padding(horizontal = 32.dp),
          title = dog.name,
          subtitle = dog.breed,
          byline = dog.ageAndSexString
      )
    }
  }
}

@Composable
private fun HeaderImage(
    modifier: Modifier = Modifier,
    imageUrl: String = ""
) {
  Box(
      modifier = modifier.aspectRatio(1.3f),
      contentAlignment = Alignment.BottomStart
  ) {
    CoilImage(
        data = imageUrl,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        fadeIn = true,
    )
  }

}

@Composable
private fun StoryContentBox(
    modifier: Modifier = Modifier,
    dogName: String
) {
  val scrollState = rememberScrollState()
  Box(
      modifier = modifier
        .verticalScroll(scrollState)
  ) {
    Column(
        modifier = Modifier.padding(
            top = 96.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = 40.dp
        )
    ) {
      Text(
          modifier = Modifier.padding(bottom = 8.dp),
          text = "${dogName}'s Story",
          style = MaterialTheme.typography.h5
      )
      Text(
          text = stringResource(id = string.dog_description_long),
          style = MaterialTheme.typography.body1,
          lineHeight = MaterialTheme.typography.body1.fontSize * 1.5
      )
    }
  }
}


