package com.hrithik.hisabkitab.ui.compose

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class GradientColor() {

    companion object {

        val expenseGradient = Brush.horizontalGradient(
            listOf(Color(0xFFea384d), Color(0xFFd31027))
        )

        val incomeGradient = Brush.horizontalGradient(
            listOf(Color(0xFF56ab2f), Color(0xFFa8e063))
        )
        val loanGradient = Brush.horizontalGradient(
            listOf(Color(0xFF2193b0), Color(0xFF6dd5ed))
        )


        val investmentGradient = Brush.horizontalGradient(
            listOf(Color(0xFFff9966), Color(0xFFff5e62))
        )
    }
}