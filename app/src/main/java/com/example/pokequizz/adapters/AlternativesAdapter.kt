package com.example.pokequizz.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.pokequizz.apiHelper.entities.Alternative
import com.example.pokequizz.R
import kotlinx.android.synthetic.main.alternative_item.view.alternative_title

class AlternativesAdapter(private val context: Context,
                          private val alternatives: List<Alternative>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return alternatives.size
    }

    override fun getItem(position: Int): Any {
        return alternatives[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val alternative = alternatives[position]

        val rowView = inflater.inflate(R.layout.alternative_item, parent, false)
        rowView.alternative_title.text = alternative.text

        return rowView
    }

}