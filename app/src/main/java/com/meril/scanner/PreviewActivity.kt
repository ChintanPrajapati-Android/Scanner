package com.meril.scanner

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.otaliastudios.cameraview.PictureResult
import kotlinx.android.synthetic.main.activity_preview.*
import kotlin.math.roundToInt


class PreviewActivity : AppCompatActivity() {

    companion object {
        var pictureResult: PictureResult? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        ivBack?.setOnClickListener {
            onBackPressed()
        }

        val height = intent.getIntExtra("height", 0)
        val width = intent.getIntExtra("width", 0)
        val widthX = intent.getFloatExtra("widthX", 0f)
        val heightX = intent.getFloatExtra("heightX", 0f)

        pictureResult?.apply {
            try {
                toBitmap(1000, 1000) { bitmap ->
                    bitmap?.let {
                        val croppedBmp: Bitmap = Bitmap.createBitmap(
                            bitmap,
                            (bitmap.width/3.0).roundToInt(),
                            (bitmap.height/2.5).roundToInt(),
                            130,
                            130
                        )
                        ivPreview?.setImageBitmap(croppedBmp)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pictureResult = null
    }
}