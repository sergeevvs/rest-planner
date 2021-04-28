package com.github.sergeevvs.restplanner

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class HorizontalSlider(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mMinValue: Int = 0
    private var mMaxValue: Int = 100
    private var mStep: Int = 1
    private var mCurrentValue: Int = mMinValue

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.HorizontalSlider,
            0, 0
        ).apply {
            try {
                mMinValue = getInteger(R.styleable.HorizontalSlider_minValue, 0)
                mMaxValue = getInteger(R.styleable.HorizontalSlider_maxValue, 100)
                mStep = getInteger(R.styleable.HorizontalSlider_step, 1)
            } finally {
                recycle()
            }
        }
    }

    fun getCurrentValue() = mCurrentValue
    fun setCurrentValue(currentValue: Int) {
        if (currentValue == mCurrentValue)
            return
        if (currentValue < mMinValue || currentValue > mMaxValue)
            throw IllegalArgumentException("Current value must be $mMinValue <= current value ($currentValue) <= $mMaxValue")

        mCurrentValue = currentValue
        invalidate()
        requestLayout()
    }
}