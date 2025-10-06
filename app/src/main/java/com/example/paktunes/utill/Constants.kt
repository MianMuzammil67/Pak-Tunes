package com.example.paktunes.utill

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar

object Constants {
    const val SONG_COLLECTION = "songs"
    const val USER = "users"
    const val FAVORITE = "favorite"

}

object ProgressBar {
    fun showProgressBar(view: View) {
        view.visibility = View.VISIBLE
    }

    fun hideProgressBar(view: View) {
        view.visibility = View.GONE
    }

}

fun Activity.showCenteredProgressBar(): ProgressBar {
    val progressBar = ProgressBar(this).apply {
        isIndeterminate = true
        val size = 100
        layoutParams = FrameLayout.LayoutParams(size, size, Gravity.CENTER)
    }

    val rootView = findViewById<ViewGroup>(android.R.id.content)
    rootView.addView(progressBar)
    return progressBar
}

fun Activity.hideProgressBar(progressBar: ProgressBar) {
    val rootView = findViewById<ViewGroup>(android.R.id.content)
    rootView.removeView(progressBar)
}


//    https://github.com/Kingbond470/Sarang
//    https://github.com/z-huang/InnerTune/blob/dev/app/src/main/java/com/zionhuang/music/playback/MusicService.kt


//    https://www.apniisp.com/songs/pakistani-pop-albums/ali-zafar-masty/390/1.html