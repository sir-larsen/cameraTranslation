package com.example.cameratranslation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


/**
 * PhrasesRecyclerAdapter:
 */
class PhrasesRecyclerAdapter(private val theme: Map<String, String>, private val listener: OnThemeClickListener) :
    RecyclerView.Adapter<PhrasesRecyclerAdapter.ViewHolder>() {

    /**
     * Create ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_phrases, parent, false)
        return ViewHolder(view)
    }

    /**
     * Set values to view
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.theme.text = theme.keys.elementAt(position)
    }

    /**
     * Set number of different keys in theme-map (number of themes)
     */
    override fun getItemCount() = theme.keys.size



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var theme: TextView = itemView.findViewById(R.id.PhraseTheme)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onFoodClick(position)
            }
        }
    }

    // Other classes must implement this function
    interface OnThemeClickListener{
        fun onFoodClick(position: Int)
    }
}