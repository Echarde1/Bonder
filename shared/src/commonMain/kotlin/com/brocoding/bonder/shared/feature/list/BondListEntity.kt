package com.brocoding.bonder.shared.feature.list

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize

@Parcelize
data class BondListEntity(
    val firstLetter: String,
    val secId: String,
    val bondName: String,
    val couponPercent: Double,
    val prevPrice: Double,
    val maturityDate: String,
    val offerDate: String, // Нужно сделать так, чтоб бек отдавал эти данные в запросе детализации
    val accumulatedCouponIncome: Double, // Нужно сделать так, чтоб бек отдавал эти данные в запросе детализации
    val duration: Int, // Нужно сделать так, чтоб бек отдавал эти данные в запросе детализации
    val listLevel: Int, // Нужно сделать так, чтоб бек отдавал эти данные в запросе детализации
    val couponValue: Double, // Нужно сделать так, чтоб бек отдавал эти данные в запросе детализации
) : Parcelable