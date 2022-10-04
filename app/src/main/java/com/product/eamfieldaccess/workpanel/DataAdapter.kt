package com.product.eamfieldaccess.workpanel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R

class DataAdapter(
    private val dataSet: Array<String>,
    private val onClick: (CharSequence) -> Unit
) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(view: View, val onClick: (CharSequence) -> Unit) :
        RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.list_pane_row_item)

        init {
            textView.setOnClickListener {
                onClick(textView.text)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter
    .ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_pane_row_item, parent,
            false
        )
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.textView.text = dataSet[position]
    }

    override fun getItemCount() = dataSet.size
}