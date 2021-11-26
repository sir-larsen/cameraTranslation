package com.example.cameratranslation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.cameratranslation.data.PhrasesDataSource
import com.example.cameratranslation.model.TranslationItem

class DetailViewModel() : ViewModel() {
    var phrases: List<TranslationItem>? = null

    fun getTransit(fraser: List<TranslationItem>) {
        phrases = fraser
    }

    fun getHealth(fraser: List<TranslationItem>) {
        phrases = fraser
    }

    fun getDining(fraser: List<TranslationItem>) {
        phrases = fraser
    }
}