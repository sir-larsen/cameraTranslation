package com.example.cameratranslation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel for both the camera fragment and translate fragment
 */
class CameraViewModel() : ViewModel() {
    var stringToTrans = MutableLiveData<String>()
    var translatedText = MutableLiveData<String>()

    var stringToTrans2 = MutableLiveData<String>()
    var translatedText2 = MutableLiveData<String>()
}