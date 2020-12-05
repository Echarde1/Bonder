package com.brocoding.bonder.shared.feature.details

data class BondDetailsEntity(
    val typeName: String,
    val name: String,
    val prevPrice: Double,
    val secSubtype: String?, // Например, обеспечена импотечными кредитами
    val couponPercent: Double,
    val couponValue: Double,
    val offerDate: String,
    val isEarlyRepayment: Boolean, // досрочный выкуп
    val initialValue: Int, // Номинал
    val maturityDate: String,
    val accumulatedCouponIncome: Double,
    val duration: Int,
    val listLevel: Int,
)