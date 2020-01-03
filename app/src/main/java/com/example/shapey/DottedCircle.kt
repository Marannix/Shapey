package com.example.shapey

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlin.math.min


class DottedCircle : View {

    private val CIRCLE_SIZE_DEF = 200f
    private val CIRCLE_RADIUS_DEF = 50f

    private lateinit var paintCircle: Paint
    private lateinit var dashPathEffect: DashPathEffect
    private var circleColor: Int = 0
    private var circleSize: Float = 0f

    private var circleX: Float = 0f
    private var circleY: Float = 0f
    private var circleRadius: Float = 0f

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
        paintCircle = Paint()
        paintCircle.color = (Color.RED)
        paintCircle.isAntiAlias = true
        dashPathEffect = DashPathEffect(floatArrayOf(20f, 20f), 1.0.toFloat())

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DottedCircle)

        circleColor = typedArray.getColor(R.styleable.DottedCircle_circle_color, Color.GREEN)
//        circleSize = typedArray.getFloat(R.styleable.DottedCircle_circle_size, CIRCLE_SIZE_DEF)
        circleRadius = typedArray.getFloat(R.styleable.DottedCircle_circle_radius, CIRCLE_RADIUS_DEF)

        paintCircle.pathEffect = dashPathEffect
        paintCircle.style = Paint.Style.STROKE

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        circleX = (width / 2).toFloat()
        circleY = (height / 2).toFloat()

        if (width > height) {
            circleRadius = (height / 2).toFloat()
        } else {
            circleRadius = (width / 2).toFloat()
        }

        canvas.drawCircle(circleX, circleY, circleRadius, paintCircle)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = circleX.toInt()
        val desiredHeight = circleY.toInt()

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width: Int
        val height: Int

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = min(desiredWidth, widthSize)
        } else {
            //Be whatever you want
            width = desiredWidth
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = min(desiredHeight, heightSize)
        } else {
            //Be whatever you want
            height = desiredHeight
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height)
    }
}