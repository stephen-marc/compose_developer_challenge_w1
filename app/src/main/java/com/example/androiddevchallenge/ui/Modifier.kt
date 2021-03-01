package com.example.androiddevchallenge.ui

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.drawColoredShadow(
    color: Color,
    alpha: Float = 0.5f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 8.dp,
    offsetY: Dp = 2.dp,
    offsetX: Dp = 2.dp
) = composed {
  this.drawBehind {
    val transparentColor = color
      .copy(alpha = 0.0f)
      .toArgb()
    val shadowColor = color
      .copy(alpha = alpha)
      .toArgb()
    this.drawIntoCanvas {
      val paint = Paint()
      val frameworkPaint = paint.asFrameworkPaint()
      frameworkPaint.color = transparentColor
      frameworkPaint.setShadowLayer(
          shadowRadius.toPx(),
          offsetX.toPx(),
          offsetY.toPx(),
          shadowColor
      )
      it.drawRoundRect(
          0f,
          0f,
          this.size.width,
          this.size.height,
          borderRadius.toPx(),
          borderRadius.toPx(),
          paint
      )
    }
  }
}

fun Modifier.gradientTint(
    colors: List<Color>,
    blendMode: BlendMode,
    brushProvider: (List<Color>) -> Brush
) = composed {
  val gradient = remember(colors) { brushProvider(colors) }
  drawWithContent {
    drawContent()
    drawRect(
        brush = gradient,
        blendMode = blendMode
    )
  }
}
