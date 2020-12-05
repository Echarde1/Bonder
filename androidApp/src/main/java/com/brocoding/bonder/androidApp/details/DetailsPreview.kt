package com.brocoding.bonder.androidApp.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.brocoding.bonder.androidApp.R
import com.brocoding.bonder.androidApp.base.BonderText
import com.brocoding.bonder.shared.feature.details.BondDetailsEntity

@Preview
@Composable
fun BondDetailsPreview() {
    MaterialTheme {
        Box(modifier = Modifier.background(Color.White)) {
            BondDetailsSuccess(
                entity = BondDetailsEntity(
                    typeName = "TypeName",
                    name = "Name",
                    prevPrice = 8.7,
                    secSubtype = null,
                    couponPercent = 1.23,
                    couponValue = 6.5,
                    offerDate = "OfferDate",
                    isEarlyRepayment = true,
                    initialValue = 1000,
                    maturityDate = "MaturityDate",
                    accumulatedCouponIncome = 123.0,
                    duration = 56,
                    listLevel = 123
                )
            )
        }
    }
}

@Composable
fun BondDetailsSuccess(entity: BondDetailsEntity) {
    val titles: List<Pair<String, String>>
    entity.run {
        titles = listOf(
            "Type_Name" to typeName,
            "Name" to name,
            "PrevPrice" to prevPrice.toString(),
            "Sec_Subtype" to secSubtype.toString(),
            "CouponPercent" to couponPercent.toString(),
            "OfferDate" to offerDate,
            "isEarlyRepaymentAvailable" to isEarlyRepayment.toString(),
            "initValue" to initialValue.toString(),
            "CouponValue" to couponValue.toString(),
            "MaturityDate" to maturityDate,
            "AccumulatedCouponIncome" to accumulatedCouponIncome.toString(),
            "Duration" to duration.toString(),
            "ListLevel" to listLevel.toString()
        )
    }

    Column(
        modifier = Modifier.padding(
            top = 24.dp,
            start = 16.dp,
            end = 16.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(vectorResource(id = R.drawable.ic_info))
            Spacer(modifier = Modifier.preferredSize(10.dp))
            BonderText(
                text = "Информация о выпуске",
                size = 14.sp,
                textColorRes = R.color.color_5222D0
            )
        }
        LazyColumnFor(items = titles) { title ->
            Row(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BonderText(title.first, size = 12.sp)
                BonderText(title.second, size = 12.sp, textColorRes = R.color.color_AEAEAE)
            }
            Spacer(modifier = Modifier.preferredSize(12.dp))
        }
    }
}