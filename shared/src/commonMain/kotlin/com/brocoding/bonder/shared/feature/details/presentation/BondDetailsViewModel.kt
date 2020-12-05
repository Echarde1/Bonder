package com.brocoding.bonder.shared.feature.details.presentation

import com.brocoding.bonder.shared.CStateFlow
import com.brocoding.bonder.shared.Response
import com.brocoding.bonder.shared.asCommonFlow
import com.brocoding.bonder.shared.data.BonderRepository
import com.brocoding.bonder.shared.data.dto.DetailsBondDTO
import com.brocoding.bonder.shared.feature.details.BondDetailsEntity
import com.brocoding.bonder.shared.feature.list.BondListEntity
import com.brocoding.bonder.shared.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BondDetailsViewModel(
    private val bondListEntity: BondListEntity,
    private val bonderRepository: BonderRepository
) : ViewModel() {

    private val _state: MutableStateFlow<BondDetailsState> =
        MutableStateFlow(BondDetailsState.Loading)

    val state: CStateFlow<BondDetailsState> = _state.asCommonFlow()

    init {
        viewModelScope.launch {
            getBondsDetails()
        }
    }

    private suspend fun getBondsDetails() {
        when (val response = bonderRepository.getBondDetails(bondListEntity.secId)) {
            is Response.Error -> _state.value = BondDetailsState.Error(response.exception)
            is Response.Success -> _state.value = BondDetailsState.Success(mapToBondDetailsModel(response.data))
        }
    }

    private fun mapToBondDetailsModel(from: DetailsBondDTO): BondDetailsEntity =
        BondDetailsEntity(
            typeName = from.type_name,
            name = from.name,
            prevPrice = bondListEntity.prevPrice,
            secSubtype = from.sec_subtype,
            couponPercent = bondListEntity.couponPercent,
            offerDate = bondListEntity.offerDate,
            isEarlyRepayment = from.is_early_repayment_available,
            initialValue = from.init_value,
            couponValue = bondListEntity.couponValue,
            maturityDate = bondListEntity.maturityDate,
            accumulatedCouponIncome = bondListEntity.accumulatedCouponIncome,
            duration = bondListEntity.duration,
            listLevel = bondListEntity.listLevel,
        )

}