package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R.drawable
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable fun DogInfoCard(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    byline: String = ""
) {
  var checkedState by remember { mutableStateOf(false) }

  Card(
      modifier = modifier,
      backgroundColor = MaterialTheme.colors.primary,
      elevation = 4.dp
  ) {
    Row(
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 32.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
          modifier = Modifier
            .weight(1f)
      ) {
        Text(text = title, style = MaterialTheme.typography.h5)
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
          Text(text = subtitle, style = MaterialTheme.typography.subtitle1)
          Text(text = byline, style = MaterialTheme.typography.subtitle1)
        }
      }
      Column(Modifier.wrapContentWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        IconToggleButton(
            modifier = Modifier,
            checked = checkedState,
            onCheckedChange = { checkedState = !checkedState }) {
          Icon(
              modifier = Modifier.fillMaxSize(),
              painter = painterResource(id = drawable.ic_pets_24),
              contentDescription = "Up navigation",
              tint = if (checkedState) MaterialTheme.colors.secondary else LocalContentColor.current
          )
        }
        Text(text = "Tap to adopt", style = MaterialTheme.typography.caption, textAlign = Center)
      }

    }

  }
}

@Preview
@Composable
fun DogInfoCardPreview() {
  MyTheme {
    DogInfoCard()
  }
}
