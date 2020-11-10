package com.brocoding.bonder.shared.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DetailsBond(
    val secid: String,
    val type: String,
    val sec_subtype: String?,
    val name: String,
    val is_for_qualified: Boolean,
    val is_early_repayment_available: Boolean,
    val init_value: Int,
    val currency: String,
    val coupon_frequency: Int
)