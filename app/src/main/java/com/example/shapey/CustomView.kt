package com.example.shapey

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable

class CustomView : View {

    private val SQUARE_SIZE = 100
    private lateinit var rectSquare: Rect
    private lateinit var paintSquare: Paint

    constructor(context: Context) : this(context, null) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(attrs)
    }

    private fun init(@Nullable attrs: AttributeSet?) {
        rectSquare = Rect()
        paintSquare = Paint(Paint.ANTI_ALIAS_FLAG)
        paintSquare.color = Color.GREEN
    }
    
    fun swapColor() {
        paintSquare.color = when (paintSquare.color) {
            Color.GREEN -> Color.RED
            else -> Color.GREEN
        }
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {

        rectSquare.left = 10
        rectSquare.top = 10
        rectSquare.right = rectSquare.left + SQUARE_SIZE
        rectSquare.bottom = rectSquare.top + SQUARE_SIZE

        canvas.drawRect(rectSquare, paintSquare)
    }
}