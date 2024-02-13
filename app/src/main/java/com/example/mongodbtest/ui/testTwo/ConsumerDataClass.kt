package com.example.mongodbtest.ui.testTwo

data class Customer(
    val name: String? = null,
    val nrc: String? = null,
)
data class Proposal(
    val paymentType: String? = null,
    val currencyType: String? = null
)
data class Driver(
    val driverName: String? = null,
    val driverNrc: String? = null
)

data class Info(
    val name: String? = null,
    val nrc: String? = null,
    val proposal: Proposal? = null,
    val driver: List<Driver>? = null
)