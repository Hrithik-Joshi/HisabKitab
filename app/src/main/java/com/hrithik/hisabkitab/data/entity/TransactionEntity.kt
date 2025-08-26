package com.hrithik.hisabkitab.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "TransactionTable")

data class TransactionEntity(

    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id: Int = 0,

    @SerialName("type")
    val type: String?,   // Expense, Income, Loan, Investment

    @SerialName("category")
    val category: String,

    @SerialName("subCategory")
    val subCategory: String,

    @SerialName("amount")
    val amount: Double,

    @SerialName("paymentMode")
    val paymentMode: String,

    @SerialName("date")
    val date: String,  // ISO 8601 format (e.g., "2023-10-01T12:00:00Z")

    @SerialName("note")
    val note: String? = null,

    )