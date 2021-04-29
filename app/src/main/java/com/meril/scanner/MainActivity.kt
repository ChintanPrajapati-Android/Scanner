package com.meril.scanner

import android.content.Intent
import android.graphics.PointF
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.otaliastudios.cameraview.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var width: Int = 0
    private var height: Int = 0
    private var fabCapture: FloatingActionButton? = null
    private var cameraView: CameraView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cameraView = findViewById(R.id.cameraView)
        fabCapture = findViewById(R.id.fabCapture)

        viewMeasure?.viewTreeObserver?.addOnGlobalLayoutListener {
            height = viewMeasure.height
            width = viewMeasure.width
        }
        cameraView?.setLifecycleOwner(this)
        cameraView?.addCameraListener(object : CameraListener() {
            override fun onCameraOpened(options: CameraOptions) {
                super.onCameraOpened(options)
            }

            override fun onCameraClosed() {
                super.onCameraClosed()
            }

            override fun onCameraError(exception: CameraException) {
                super.onCameraError(exception)
            }

            override fun onPictureTaken(result: PictureResult) {
                super.onPictureTaken(result)
                PreviewActivity.pictureResult = result
                val intent = Intent(this@MainActivity, PreviewActivity::class.java)
                intent.putExtra("height", height)
                intent.putExtra("width", width)
                intent.putExtra("widthX", viewMeasure.x)
                intent.putExtra("heightX", viewMeasure.y)
                startActivity(intent)
            }

            override fun onVideoTaken(result: VideoResult) {
                super.onVideoTaken(result)
            }

            override fun onOrientationChanged(orientation: Int) {
                super.onOrientationChanged(orientation)
            }

            override fun onAutoFocusStart(point: PointF) {
                super.onAutoFocusStart(point)
            }

            override fun onAutoFocusEnd(successful: Boolean, point: PointF) {
                super.onAutoFocusEnd(successful, point)
            }

            override fun onZoomChanged(
                newValue: Float,
                bounds: FloatArray,
                fingers: Array<out PointF>?
            ) {
                super.onZoomChanged(newValue, bounds, fingers)
            }

            override fun onExposureCorrectionChanged(
                newValue: Float,
                bounds: FloatArray,
                fingers: Array<out PointF>?
            ) {
                super.onExposureCorrectionChanged(newValue, bounds, fingers)
            }

            override fun onVideoRecordingStart() {
                super.onVideoRecordingStart()
            }

            override fun onVideoRecordingEnd() {
                super.onVideoRecordingEnd()
            }

            override fun onPictureShutter() {
                super.onPictureShutter()
            }
        })
        cameraView?.addFrameProcessor {

        }

        fabCapture?.setOnClickListener {
            cameraView?.takePicture()
        }
    }
}