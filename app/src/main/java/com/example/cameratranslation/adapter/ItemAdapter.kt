package com.example.cameratranslation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cameratranslation.R
import com.example.cameratranslation.model.TranslationItem

class ItemAdapter(
    private val dataset: List<TranslationItem>,
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an translation object.
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textOriginal: TextView = view.findViewById(R.id.item_text)
        val textTranslated: TextView = view.findViewById(R.id.item_translation)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textOriginal.text = item.originalText
        holder.textTranslated.text = item.translatedText
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */

    override fun getItemCount() = dataset.size
}