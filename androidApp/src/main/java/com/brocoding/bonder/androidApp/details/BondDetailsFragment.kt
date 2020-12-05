package com.brocoding.bonder.androidApp.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.brocoding.bonder.androidApp.R
import com.brocoding.bonder.androidApp.base.BonderError
import com.brocoding.bonder.androidApp.base.BonderText
import com.brocoding.bonder.androidApp.getFragmentArgument
import com.brocoding.bonder.shared.data.BonderRepository
import com.brocoding.bonder.shared.feature.details.BondDetailsEntity
import com.brocoding.bonder.shared.feature.details.presentation.BondDetailsState
import com.brocoding.bonder.shared.feature.details.presentation.BondDetailsViewModel
import com.brocoding.bonder.shared.feature.list.BondListEntity
import com.brocoding.bonder.shared.service_locator.ServiceLocator
import kotlinx.coroutines.flow.collect

internal class BondDetailsFragment : Fragment() {

    companion object {

        const val ARG_BOND_LIST_ENTITY = "ARG_BOND_LIST_ENTITY"

    }

    private val detailsViewModel: BondDetailsViewModel by viewModels(
        factoryProducer = {
            BondDetailViewModelFactory(getFragmentArgument(ARG_BOND_LIST_ENTITY))
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                BondDetailsScreen()
            }
        }
    }

    @Composable
    private fun BondDetailsScreen() {
        val state: BondDetailsState by detailsViewModel.state.origin.collectAsState()

        BondDetails(state)
    }

    @Composable
    private fun BondDetails(state: BondDetailsState) {
        when (state) {
            BondDetailsState.Loading -> Text("Zagruuuuuzka")
            is BondDetailsState.Success -> BondDetailsSuccess(state.result)
            is BondDetailsState.Error -> BonderError()
        }
    }

    @Composable
    private fun BondDetailsSuccess(entity: BondDetailsEntity) {
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

    private class BondDetailViewModelFactory(
        private val bondListEntity: BondListEntity
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass
                .getConstructor(BondListEntity::class.java, BonderRepository::class.java)
                .newInstance(bondListEntity, ServiceLocator.bonderRepository)
        }
    }
}