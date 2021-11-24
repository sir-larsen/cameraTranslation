package com.example.cameratranslation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.TranslateAnimation
import android.view.translation.Translator
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


////Translation her: https://developers.google.com/ml-kit/language/translation/android

        var transTekst = "Guten tag ich bin eine penis"
        var kode = ""
        val languageIdentifier = LanguageIdentification.getClient()
        languageIdentifier.identifyLanguage(transTekst)
            .addOnSuccessListener { languageCode ->
                if (languageCode == "und") {
                    println("CANNOT IDENTIFYYYYYY")
                } else {

                    kode = languageCode
                    println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
                    println(kode)

                    val options = TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.fromLanguageTag(kode))
                        .setTargetLanguage(TranslateLanguage.ENGLISH)
                        .build()
                    val norwegianEnglishTranslator = Translation.getClient(options)

                    var conditions = DownloadConditions.Builder()
                        .requireWifi()
                        .build()
                    norwegianEnglishTranslator.downloadModelIfNeeded(conditions)
                        .addOnSuccessListener {

                            norwegianEnglishTranslator.translate(transTekst)
                                .addOnSuccessListener { translatedText ->
                                    println(translatedText)
                                }
                                .addOnFailureListener { exception ->

                                }

                        }
                        .addOnFailureListener { exception ->

                            println(exception)

                        }
                }
            }
            .addOnFailureListener {
                // Model couldn’t be loaded or other internal error.
                // ...
            }

/*
        if (kode != "") {
            val options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.fromLanguageTag(kode))
                .setTargetLanguage(TranslateLanguage.ENGLISH)
                .build()
            val norwegianEnglishTranslator = Translation.getClient(options)

            var conditions = DownloadConditions.Builder()
                .requireWifi()
                .build()
            norwegianEnglishTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener {

                    norwegianEnglishTranslator.translate(transTekst)
                        .addOnSuccessListener { translatedText ->
                            println(translatedText)
                        }
                        .addOnFailureListener { exception ->

                        }

                }
                .addOnFailureListener { exception ->

                    println(exception)

                }

        } */

        //////////////////////////////////////////////////////////////////////////////////////////////////




        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController)

        //Observers, if any
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}