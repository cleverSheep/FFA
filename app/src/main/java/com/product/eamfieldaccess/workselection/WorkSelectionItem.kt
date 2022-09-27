package com.product.eamfieldaccess.workselection

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.product.eamfieldaccess.R

class WorkSelectionItem : ConstraintLayout {
    private lateinit var itemName: TextView
    private lateinit var itemContent: TextView

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
            R.layout.work_order_item_piece,
            this,
            true
        )
        itemName = ViewCompat.requireViewById(this, R.id.piece_name)
        itemContent = ViewCompat.requireViewById(this, R.id.piece_content)

        with(context.obtainStyledAttributes(attrs, R.styleable.WorkSelectionItem)) {
            try {
                getString(R.styleable.WorkSelectionItem_name)?.also { name ->
                    setName(name)
                }
                getString(R.styleable.WorkSelectionItem_content)?.also { content ->
                    setContent(content)
                }
            } finally {
                recycle()
            }
        }
    }

    fun setName(name: String?) {
        itemName.text = name
    }

    fun setContent(content: String?) {
        itemContent.text = content
    }
}