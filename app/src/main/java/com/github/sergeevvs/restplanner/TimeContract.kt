package com.github.sergeevvs.restplanner

interface TimeContract {
    interface TimeFragment
    interface TimePresenter {
        fun onTimeChanged()
    }
}