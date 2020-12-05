package com.brocoding.bonder.shared.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ListBondDTO(
    val secid: String,
    val sec_name: String,
    val prev_price: Double,
    val offer_date: String,
    val maturity_date: String,
    val list_level: Int,
    val isin: String,
    val duration: Int,
    val coupon_value: Double,
    val coupon_percent: Double,
    val board_id: String,
    val accumulated_coupon_income: Double
)