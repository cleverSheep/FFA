package com.product.eamfieldaccess.workpanel.tabs.checklist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.product.eamfieldaccess.R

class CheckListItem : ConstraintLayout {
    private lateinit var itemName: TextView
    private lateinit var checkList: ListView
    private lateinit var arrayAdapter: BaseAdapter

    constructor(context: Context) : super(context) {
        initializeView(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initializeView(attrs)
    }

    private fun initializeView(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(
            R.layout.check_list_item,
            this,
            true
        )
        itemName = ViewCompat.requireViewById(this, R.id.title)
        checkList = ViewCompat.requireViewById(this, R.id.list_items)

        with(context.obtainStyledAttributes(attrs, R.styleable.CheckListItem)) {
            try {
                getString(R.styleable.CheckListItem_name)?.also { name ->
                    setName(name)
                }
            } finally {
                recycle()
            }
        }
    }

    fun setName(name: String?) {
        itemName.text = name
    }

    fun addCheckList(items: List<String>) {
        arrayAdapter = CheckListAdapter(items, context)
        checkList.adapter = arrayAdapter
    }
}

