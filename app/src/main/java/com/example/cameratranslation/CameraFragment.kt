package com.example.cameratranslation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import com.example.cameratranslation.databinding.FragmentCameraBinding
import com.example.cameratranslation.viewmodels.CameraViewModel
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import java.lang.StringBuilder
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass.
 * Use the [CameraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CameraFragment : androidx.fragment.app.Fragment() {
    private val CAMERA_PERMISSION_REQUEST = 100

    private var mCameraSource by Delegates.notNull<CameraSource>()
    private var textRecognizer by Delegates.notNull<TextRecognizer>()

    private val camViewModel: CameraViewModel by activityViewModels() //ViewModel for translations
    private var binding: FragmentCameraBinding? = null //Databinding

    //var act = activity as MainActivity //Instance of main activity to call the translate methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentCameraBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_camera, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = camViewModel
            lifecycleOwner = viewLifecycleOwner
            cameraFragment = this@CameraFragment
        }
        startCamera()

        //Set click listeners here
        binding?.materialCardView?.setOnClickListener {
            var act = activity as MainActivity
            var translatedText = camViewModel.stringToTrans.value?.let { it1 -> act.translate(it1) }
            camViewModel.translatedText.value = translatedText
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun startCamera() {
        //Creating the text recognizer
        textRecognizer = TextRecognizer.Builder(activity?.applicationContext!!).build()

        if (!textRecognizer.isOperational) { //If not yet loaded
            Toast.makeText(activity, "Dependencies haven't loaded yet." +
                    "Please wait a few moments", Toast.LENGTH_LONG).show()
            println("DEPENDENCIES ARE LOADING")
            return
        }

        //Initialize the source for our camera, have to use high resolution and auto focus for this to work
        mCameraSource = CameraSource.Builder(activity?.applicationContext!!, textRecognizer)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedPreviewSize(1280, 1024)
            .setAutoFocusEnabled(true)
            .setRequestedFps(2.0f)
            .build()

        binding?.surfaceCameraPreview?.holder?.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {
                mCameraSource.stop()
            }

            @SuppressLint("MissingPermission")
            override fun surfaceCreated(p0: SurfaceHolder) {
                try {
                    if (isCameraGranted()) {
                        mCameraSource.start(binding?.surfaceCameraPreview!!.holder)
                    } else {
                        requestCameraPermission()
                    }
                } catch (e: Exception) {
                    Toast.makeText(activity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        })

        textRecognizer.setProcessor(object : Detector.Processor<TextBlock> {
            override fun release() {
            }

            override fun receiveDetections(p0: Detector.Detections<TextBlock>) {
                val items = p0.detectedItems

                if (items.size() <= 0) {
                    return
                }

                binding?.textToTranslate?.post {
                    var stringBuilder = StringBuilder()
                    for (i in 0 until items.size()) {
                        val item = items.valueAt(i)
                        stringBuilder.append(item.value)
                        stringBuilder.append("\n")
                    }
                    //camViewModel.stringToTrans.value = stringBuilder.toString()
                    //binding?.textToTranslate!!.text = stringBuilder.toString()
                    camViewModel.stringToTrans.value = stringBuilder.toString()
                }
            }
        })
    }

    fun isCameraGranted(): Boolean {
        return activity?.let { ActivityCompat.checkSelfPermission(it.applicationContext, Manifest.permission.CAMERA) } == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != CAMERA_PERMISSION_REQUEST) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (isCameraGranted()) {
                mCameraSource.start(binding?.surfaceCameraPreview?.holder!!)
            } else {
                Toast.makeText(activity, "NEED TO GRANT PERMISSION", Toast.LENGTH_LONG).show()
                activity?.onBackPressed()
            }
        }
    }
}