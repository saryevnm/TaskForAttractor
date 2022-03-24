package com.it.taskforattractor.ui.gallery

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.it.taskforattractor.data.remote.ServiceApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GalleryRepositoryImpl(
    private val serviceApi: ServiceApi,
) : GalleryRepository {

    private val intent: Intent
        get() {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/png"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            return intent
        }

    private var launcher: ActivityResultLauncher<Intent>? = null
    private var onPicturesReady: (pictures: ArrayList<Uri>) -> Unit? = {}

    override fun getPictures(onPicturesReady: (pictures: ArrayList<Uri>) -> Unit) {
        this.onPicturesReady = onPicturesReady
        launcher?.launch(intent)
    }

    override fun setUpLauncher(
        mRegistry: ActivityResultRegistry
    ) {
        launcher = mRegistry.register(
            "key",
            StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val count = result.data?.clipData?.itemCount
                val listOfPictures = ArrayList<Uri>()
                if (result.data?.clipData != null && count != null) {

                    for (i in 0 until count)
                        result.data?.clipData?.getItemAt(i)?.uri?.let { listOfPictures.add(it) }

                } else if (result.data?.data != null) {
                    result.data?.data?.let {
                        listOfPictures.add(it)
                    }
                }

                CoroutineScope(Dispatchers.Main).launch {
                    onPicturesReady(listOfPictures)
                }
            }
        }
    }
}