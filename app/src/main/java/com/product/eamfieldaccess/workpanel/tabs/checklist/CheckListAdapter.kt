package com.product.eamfieldaccess.workpanel.tabs.checklist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.product.eamfieldaccess.R
import com.product.eamfieldaccess.models.CheckList

class CheckListAdapter(private val checkList: List<String>, val context: Context) : BaseAdapter() {
    override fun getCount(): Int = checkList.size

    override fun getItem(p0: Int) = null

    override fun getItemId(p0: Int) = 0L

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.check_list_sub_item, null)
        val number = view.findViewById<TextView>(R.id.item_number)
        val content = view.findViewById<TextView>(R.id.item_content)
        number.text = "${position + 1})"
        content.text = checkList[position]
        return view
    }
}

class CheckListMainAdapter(
    private val dataSet: ArrayList<CheckList>
) : RecyclerView.Adapter<CheckListMainAdapter.ViewHolder>() {

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val checkListItem = view.findViewById<CheckListItem>(R.id.main_check_list_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.check_list_main_item, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.checkListItem.setName(dataSet[position].title)
        holder.checkListItem.addCheckList(dataSet.map {
            it.items
        }.flatten())
    }

    override fun getItemCount() = dataSet.size
}