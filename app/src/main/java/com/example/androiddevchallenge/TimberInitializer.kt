package com.example.androiddevchallenge

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber
import timber.log.Timber.DebugTree

class TimberInitializer : Initializer<Unit> {
  override fun create(context: Context) {
    Timber.plant(DebugTree())
  }

  override fun dependencies() = emptyList<Class<out Initializer<*>>>()

}
