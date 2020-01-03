package com.example.shapey

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Nullable
import kotlin.math.pow

class CustomView : View {

    private val SQUARE_SIZE_DEF = 200
    private lateinit var rectSquare: Rect
    private lateinit var paintCircle: Paint
    private lateinit var paintSquare: Paint
    private var squareColor: Int = 0
    private var squareSize: Int = 0

    private var circleX: Float = 0f
    private var circleY: Float = 0f
    private var circleRadius: Float = 100f

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

        paintCircle = Paint()
        paintCircle.isAntiAlias = true
        paintCircle.color = Color.parseColor("#2b3648")
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)

        squareColor = typedArray.getColor(R.styleable.CustomView_square_color, Color.GREEN)
        squareSize = typedArray.getDimensionPixelSize(R.styleable.CustomView_square_size, SQUARE_SIZE_DEF)

        paintSquare.color = squareColor
        typedArray.recycle()
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
        rectSquare.right = rectSquare.left + squareSize
        rectSquare.bottom = rectSquare.top + squareSize

        canvas.drawRect(rectSquare, paintSquare)

        if (circleX == 0f || circleY == 0f) {
            circleX = (width / 2).toFloat()
            circleY = (height / 2).toFloat()
        }

        canvas.drawCircle(circleX, circleY, circleRadius, paintCircle)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val value = super.onTouchEvent(event)

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = event.x
                val y = event.y

                if (rectSquare.left < x && rectSquare.right > x) {
                    if (rectSquare.top < y && rectSquare.bottom > y) {
                        circleRadius += 10f
                        postInvalidate()
                    }
                }
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                val x = event.x
                val y = event.y

                val dx = (x - circleX).toDouble().pow(2)
                val dy = (y - circleY).toDouble().pow(2)

                if (dx + dy < (circleRadius).pow(2)) {
                    // Touched
                    circleX = x
                    circleY = y
                    postInvalidate()
                    return true
                }

            }
        }
        return value
    }
}