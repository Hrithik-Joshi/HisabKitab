package com.hrithik.hisabkitab.util

class LoanCategories {
    companion object {

        val loanCategoriesWithSubcategories = mapOf(
            "Personal Loan" to listOf(
                "Bank Loan",
                "Instant App Loan",
                "Credit Card Loan"
            ),
            "Home Loan" to listOf(
                "Bank EMI",
                "Renovation Loan",
                "Housing Finance"
            ),
            "Education Loan" to listOf(
                "Student Loan",
                "Coaching Loan"
            ),
            "Car/Auto Loan" to listOf(
                "Car EMI",
                "Two-Wheeler EMI"
            ),
            "Borrowed from Family/Friends" to listOf(
                "Short-term",
                "Long-term"
            ),
            "Business Loan" to listOf(
                "MSME Loan",
                "Working Capital Loan"
            ),
            "Other" to listOf(
                "Medical Loan",
                "Emergency Loan"
            )
        )

    }

}