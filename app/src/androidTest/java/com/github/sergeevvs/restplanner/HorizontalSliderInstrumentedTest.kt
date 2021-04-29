package com.github.sergeevvs.restplanner

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HorizontalSliderInstrumentedTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val slider = HorizontalSlider(context, null)
    private var diff = (slider.maxValue - slider.minValue) / 2

    // Current value
    @Test
    fun setCurrentValue_valid() {
        slider.currentValue = diff
        Assert.assertEquals(slider.currentValue, diff)

        slider.currentValue = slider.minValue
        Assert.assertEquals(slider.currentValue, slider.minValue)

        slider.currentValue = slider.maxValue
        Assert.assertEquals(slider.currentValue, slider.maxValue)
    }

    @Test
    fun setCurrentValue_invalid() {
        slider.currentValue = diff

        try {
            slider.currentValue = slider.minValue - 1
        } catch (e: IllegalArgumentException) {
            System.err.println(e.message)
        }
        Assert.assertEquals(slider.currentValue, diff)

        try {
            slider.currentValue = slider.maxValue + 1
        } catch (e: IllegalArgumentException) {
            System.err.println(e.message)
        }
        Assert.assertEquals(slider.currentValue, diff)
    }

}