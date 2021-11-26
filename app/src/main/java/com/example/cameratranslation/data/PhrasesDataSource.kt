package com.example.cameratranslation.data

import android.content.Context
import com.example.cameratranslation.R
import com.example.cameratranslation.model.TranslationItem


object PhrasesDataSource {
    fun loadTransitPhrases(ctx: Context): List<TranslationItem> {
        return listOf<TranslationItem>(
            TranslationItem(ctx.resources.getString(R.string.transit1), ""),
            TranslationItem(ctx.resources.getString(R.string.transit2), ""),
            TranslationItem(ctx.resources.getString(R.string.transit3), "")
        )
    }

    fun loadHealthPhrases(ctx: Context): List<TranslationItem> {
        return listOf<TranslationItem>(
            TranslationItem(ctx.resources.getString(R.string.health1), ""),
            TranslationItem(ctx.resources.getString(R.string.health2), ""),
            TranslationItem(ctx.resources.getString(R.string.health3), "")
        )
    }

    fun loadDiningPhrases(ctx: Context): List<TranslationItem> {
        return listOf<TranslationItem>(
            TranslationItem(ctx.resources.getString(R.string.Dining1), ""),
            TranslationItem(ctx.resources.getString(R.string.Dining2), ""),
            TranslationItem(ctx.resources.getString(R.string.Dining3), "")
        )
    }
}