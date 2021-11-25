package com.example.cameratranslation

import android.content.Context



class PhrasesDataSource(private val ctx: Context) {
    /**
     * Loads list of strings from resources
     */
     fun loadPhrase(): Map<String, Array<String>>{
     return mapOf<String, Array<String>>(
         Pair("Transit", ctx.resources.getStringArray(R.array.Transit)),
         Pair("Health", ctx.resources.getStringArray(R.array.Health)),
         Pair("Accommodation",ctx.resources.getStringArray(R.array.Accommodation)),
         Pair("Dining",ctx.resources.getStringArray(R.array.Dining)),
         Pair("Shopping",ctx.resources.getStringArray(R.array.Shopping)),
         Pair("Greeting", ctx.resources.getStringArray(R.array.Greeting)),
         Pair("Emergency",ctx.resources.getStringArray(R.array.Emergency))
     )
    }

}