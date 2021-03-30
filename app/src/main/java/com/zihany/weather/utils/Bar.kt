package com.zihany.weather.utils

import android.graphics.Color
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity

private fun Window.transparentStatusBar() {
    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    val option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    val vis = decorView.systemUiVisibility
    decorView.systemUiVisibility = option or vis
    statusBarColor = Color.TRANSPARENT
}

fun ComponentActivity.transparentStatusBar() {
    window.transparentStatusBar()
}