package com.brocoding.bonder.shared.feature.list.presentation

data class BondListModel(
    val firstLetter: String,
    val secId: String,
    val bondName: String,
    val couponPercent: Double,
    val prevPrice: Double,
    val maturityDate: String
)