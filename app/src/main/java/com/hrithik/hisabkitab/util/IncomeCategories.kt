package com.hrithik.hisabkitab.util

class IncomeCategories {
    companion object {

        val incomeCategoriesWithSubcategories = mapOf(
            "Salary" to listOf(
                "Monthly Paycheck",
                "Bonus",
                "Commission",
                "Overtime"
            ),
            "Business" to listOf(
                "Freelance",
                "Side Hustle",
                "Sales",
                "Consulting"
            ),
            "Interest" to listOf(
                "Bank Savings",
                "FD Interest",
                "Mutual Fund Interest"
            ),
            "Dividend" to listOf("Stocks", "Mutual Funds"),
            "Rental Income" to listOf(
                "House Rent",
                "Commercial Rent",
                "Health Insurance",
                "Gym / Fitness"
            ),
            "Gift" to listOf(
                "Cash Gift",
                "Festive Gift",
                "Family Support"
            ),
            "Refunds" to listOf("Tax Refund", "Return on Purchase", "Cashback"),
            "Other" to listOf("Other")
        )
    }
}