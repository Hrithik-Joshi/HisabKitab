package com.hrithik.hisabkitab.util

class InvestmentCategories {

    companion object {
        val investmentCategoriesWithSubcategories = mapOf(
            "Stock Market" to listOf(
                "Equity",
                "Derivatives",
                "SIP",
                "ETF"
            ),
            "Mutual Funds" to listOf(
                "SIP",
                "Lumpsum",
                "ELSS"
            ),
            "Real Estate" to listOf(
                "Plot",
                "Apartment",
                "Commercial Property"
            ),
            "Fixed Deposit" to listOf(
                "Bank FD",
                "Company FD"
            ),
            "Gold" to listOf(
                "Physical Gold",
                "Gold Bonds",
                "Digital Gold"
            ),
            "Cryptocurrency" to listOf(
                "Bitcoin",
                "Ethereum",
                "Altcoins"
            ),
            "Public Schemes" to listOf(
                "PPF",
                "NPS",
                "Post Office Saving"
            ),
            "Insurance (Investing)" to listOf(
                "ULIP",
                "Endowment Plan"
            ),
            "Other" to listOf(
                "Crowdfunding",
                "Angel Investment",
                "Peer-to-Peer Lending"
            )
        )

    }
}