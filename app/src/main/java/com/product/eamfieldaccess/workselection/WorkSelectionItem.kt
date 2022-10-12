package com.product.eamfieldaccess.workselection

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.product.eamfieldaccess.R

class WorkSelectionItem : LinearLayout {
    private lateinit var itemName: TextView
    private lateinit var itemContent: TextView
    private lateinit var editableTextField: EditText

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
        editableTextField = ViewCompat.requireViewById(this, R.id.editable_text_field)

        orientation = VERTICAL

        with(context.obtainStyledAttributes(attrs, R.styleable.WorkSelectionItem)) {
            try {
                getString(R.styleable.WorkSelectionItem_name)?.also { name ->
                    setName(name)
                }
                getString(R.styleable.WorkSelectionItem_content)?.also { content ->
                    setContent(content)
                }
                getString(R.styleable.WorkSelectionItem_editableText)?.also { content ->
                    setContentForEditableText(content)
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
        itemContent.visibility = View.VISIBLE
        itemContent.text = content
        editableTextField.visibility = View.GONE
    }

    fun setContentForEditableText(content: String?) {
        editableTextField.visibility = View.VISIBLE
        editableTextField.setText(content)
        itemContent.visibility = View.GONE
    }
}