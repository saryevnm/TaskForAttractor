package com.it.taskforattractor.ui.gallery

import android.net.Uri
import androidx.activity.result.ActivityResultRegistry
import androidx.lifecycle.ViewModel

class GalleryViewModel(private val repository: GalleryRepository) : ViewModel() {

    fun getPictures(onPicturesReady: (pictures: ArrayList<Uri>) -> Unit) {
        repository.getPictures(onPicturesReady)
    }

    fun setUpLauncher(mRegistry: ActivityResultRegistry) {
        repository.setUpLauncher(mRegistry)
    }
}