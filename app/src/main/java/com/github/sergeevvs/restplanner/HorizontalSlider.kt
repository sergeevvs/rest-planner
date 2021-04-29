package com.github.sergeevvs.restplanner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

class HorizontalSlider(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    companion object {
        const val SELECTOR_ITEM_COUNT = 3
    }

    @ColorInt
    private var mTextColor: Int = context.getColorFromAttr(R.attr.colorAccent)
    private var mMinValue: Int = 0
    private var mMaxValue: Int = 100
    private var mStep: Int = 1
    private var mCurrentValue: Int = mMinValue

    init {
        setWillNotDraw(false)
        /*
            context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.HorizontalSlider,
                0, 0
            ).apply {
                try {
                    // Enter here your custom xml attrs
                } finally {
                    recycle()
                }
            }*/
    }

    var minValue
        get() = mMinValue
        set(value) {
            if (value == mMinValue) return
            if (value >= mMaxValue)
                throw IllegalArgumentException("min value($value) must be < max value($mMaxValue)")

            mMinValue = value
            invalidate()
        }

    var maxValue
        get() = mMaxValue
        set(value) {
            if (value == mMaxValue) return
            if (value <= mMinValue)
                throw IllegalArgumentException("max value($value) must be > min value($mMinValue)")

            mMaxValue = value
            invalidate()
        }

    var step
        get() = mStep
        set(value) {
            if (value == mStep) return
            if (value <= 0)
                throw IllegalArgumentException("step($value) must be > 0")

            mStep = value
            invalidate()
        }

    var currentValue
        get() = mCurrentValue
        set(value) {
            if (value == mCurrentValue) return
            if (value < mMinValue || value > mMaxValue)
                throw IllegalArgumentException("current value($value) must be >= min value($mMinValue) AND <= max value($mMaxValue)")

            mCurrentValue = value
            invalidate()
        }


    /*private val textPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = mTextColor
        textSize = 20f
    }

    private val sliderPaint = Paint(ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textSize = 20f
    }

    private val shadowPaint = Paint(0).apply {
        color = 0x101010
        maskFilter = BlurMaskFilter(8f, BlurMaskFilter.Blur.NORMAL)
    }*/

    // testing
    private val paint = Paint(ANTI_ALIAS_FLAG)
    private var sliderColor = Color.WHITE
    private var pickerColor = Color.BLACK
    private var borderWidth = 4.0f
    private var size = 320
    // end testing

    /*override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }*/

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawSlider(canvas)
    }

    private fun drawSlider(canvas: Canvas) {
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL

        val radius = size / 2f

        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawPicker(canvas: Canvas) {

    }

    /*override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        var xpad = (paddingLeft + paddingRight).toFloat()
        val ypad = (paddingTop + paddingBottom).toFloat()

        // FIXME: 29.04.2021 create textHeight property
        xpad += 20f

        val ww = w.toFloat() - xpad
        val hh = h.toFloat() - ypad

        val diameter = min(ww, hh)
    }*/

    @ColorInt
    fun Context.getColorFromAttr(
        @AttrRes attrColor: Int,
        typedValue: TypedValue = TypedValue(),
        resolveRefs: Boolean = true
    ): Int {
        theme.resolveAttribute(attrColor, typedValue, resolveRefs)
        return typedValue.data
    }
}