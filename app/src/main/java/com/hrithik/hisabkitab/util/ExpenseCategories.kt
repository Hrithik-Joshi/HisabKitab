package com.hrithik.hisabkitab.util

class ExpenseCategories {

    companion object{

        val expenseCategoriesWithSubcategories = mapOf(
            "Food & Dining" to listOf(
                "Groceries",
                "Restaurants",
                "Coffee & Snacks",
                "Fast Food",
                "Delivery"
            ),
            "Transportation" to listOf(
                "Fuel",
                "Cab / Taxi",
                "Public Transport",
                "Parking",
                "Vehicle Maintenance"
            ),
            "Housing" to listOf(
                "Rent",
                "Utilities (Electricity, Water, Gas)",
                "Internet",
                "Repairs & Maintenance"
            ),
            "Shopping" to listOf("Clothing", "Electronics", "Home Supplies", "Personal Care", "Gifts"),
            "Health & Medical" to listOf(
                "Doctor Visits",
                "Medicines",
                "Health Insurance",
                "Gym / Fitness"
            ),
            "Entertainment" to listOf(
                "Movies / OTT",
                "Events / Concerts",
                "Games",
                "Subscriptions (Netflix, Spotify, etc.)"
            ),
            "Education" to listOf("Tuition", "Books", "Courses (Online/Offline)"),
            "Bills & EMI" to listOf("Credit Card Payment", "Loan EMI", "Mobile Recharge / Bill"),
            "Travel" to listOf(
                "Hotel",
                "Flights / Train / Bus",
                "Tourism Activities",
                "Travel Insurance"
            ),
            "Family & Kids" to listOf("Childcare", "School Fees", "Toys", "Elder Care"),
            "Pets" to listOf("Food", "Vet", "Grooming"),
            "Other" to listOf("Other")
        )

        val paymentModes = listOf(
            "Cash", "UPI", "Debit Card", "Credit Card", "Net Banking", "Other"
        )
    }
}