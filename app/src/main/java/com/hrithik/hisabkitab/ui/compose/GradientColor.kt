package com.hrithik.hisabkitab.ui.compose

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class GradientColor() {

    companion object {

        val expenseGradient = Brush.horizontalGradient(
            listOf(Color(0xFFbc556f), Color(0xFFf9a470))
        )

        val incomeGradient = Brush.horizontalGradient(
            listOf(Color(0xFF5fc52e), Color(0xFF6eee87))
        )
        val loanGradient = Brush.horizontalGradient(
            listOf(Color(0xFF5ab2f7), Color(0xFF12cff3))
        )


        val investmentGradient = Brush.horizontalGradient(
            listOf(Color(0xFFf4762d), Color(0xFFffd78a))
        )
    }
}