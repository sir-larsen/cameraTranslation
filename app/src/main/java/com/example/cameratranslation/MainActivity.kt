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
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.NORWEGIAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        val norwegianEnglishTranslator = Translation.getClient(options)

        var conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        norwegianEnglishTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {

                norwegianEnglishTranslator.translate("spis bæsj")
                    .addOnSuccessListener { translatedText ->
                        println(translatedText)
                    }
                    .addOnFailureListener { exception ->

                    }

            }
            .addOnFailureListener { exception ->

                println(exception)

            }

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