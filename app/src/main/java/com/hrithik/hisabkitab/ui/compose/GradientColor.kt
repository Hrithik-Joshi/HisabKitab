package com.hrithik.hisabkitab.ui.compose

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class GradientColor() {

    companion object {

        val expenseGradient = Brush.horizontalGradient(
            listOf(
                Color(0xFFFFD5D5), Color(0xFFFF8C8C))
        )
        val incomeGradient = Brush.horizontalGradient(
            listOf(Color(0xFFDFFFD9), Color(0xFF9DFF9C))
        )
        val loanGradient = Brush.horizontalGradient(
            listOf(Color(0xFFD6F4FF), Color(0xFFA7E2FF))
        )
        val investmentGradient = Brush.horizontalGradient(
            listOf(Color(0xFFFFE4C4), Color(0xFFFFCBA4))
        )
        val expenseAdd = Brush.horizontalGradient(
            listOf(Color(0xFFFBC2EB), Color(0xFFA6C1EE))
        )
    }
}