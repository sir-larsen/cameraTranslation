package com.example.cameratranslation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cameratranslation.R


/**
 * PhrasesRecyclerAdapter:
 */
class PhrasesRecyclerAdapter(
    private val context: Context,
    private val data: Map<String, Array<String>>,
    private val listener: OnThemeClickListener
): RecyclerView.Adapter<PhrasesRecyclerAdapter.ViewHolder>() {



    /**
     * Create ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.cardview_phrases, parent, false)
        return ViewHolder(adapterLayout)
    }

    /**
     * Set values to view
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.theme.text = data.keys.elementAt(position)
    }

    /**
     * Set number of different keys in theme-map (number of themes)
     */
    override fun getItemCount() = data.keys.size



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var theme: TextView = itemView.findViewById(R.id.PhraseTheme)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onThemeClick(position)
            }
        }
    }

    // Other classes must implement this function
    interface OnThemeClickListener{
        fun onThemeClick(position: Int)
    }
}