package com.it.taskforattractor.ui.gallery

import android.net.Uri
import androidx.activity.result.ActivityResultRegistry

interface GalleryRepository {
    fun getPictures(onPicturesReady: (pictures: ArrayList<Uri>) -> Unit)
    fun setUpLauncher(mRegistry: ActivityResultRegistry)
}