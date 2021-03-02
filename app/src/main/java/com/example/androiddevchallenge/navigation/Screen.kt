package com.example.androiddevchallenge.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

sealed class Screen(val identifier: String) {
  abstract fun route(): String
}

object MainScreen : Screen("main_screen") {
  override fun route(): String = identifier
}

object DetailScreen : Screen("detail_screen") {
  val dogNameArg = "dogName"

  override fun route() = "$identifier/{$dogNameArg}"
}

class Actions(navController: NavHostController) {
  val openDetails: (String) -> Unit = { dogName ->
    navController.navigate("${DetailScreen.identifier}/$dogName")
  }
}
