package com.brocoding.bonder.shared.feature.list.presentation

import com.brocoding.bonder.shared.CStateFlow
import com.brocoding.bonder.shared.Response
import com.brocoding.bonder.shared.data.BonderRepository
import com.brocoding.bonder.shared.data.dto.ListBond
import com.brocoding.bonder.shared.service_locator.ServiceLocator
import com.brocoding.bonder.shared.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BondsListViewModel(
    private val bonderRepository: BonderRepository = ServiceLocator.bonderRepository
) : ViewModel() {

    private val _state: MutableStateFlow<BondsListState> = MutableStateFlow(BondsListState.Loading)
    val state: CStateFlow<BondsListState> =  _state.asCommonFlow()

    init {
        viewModelScope.launch {
            getBondsList()
        }
    }

    private suspend fun getBondsList() {
        when (val response = bonderRepository.getBondsList()) {
            is Response.Error -> _state.value = BondsListState.Error(response.exception)
            is Response.Success -> _state.value = BondsListState.Success(mapToBondListModel(response.data))
        }
    }

    private fun mapToBondListModel(from: List<ListBond>): List<BondListModel> = from.map {
        BondListModel(
            firstLetter = run {
                val regexpPattern = "([а-яА-Я])|([a-zA-Z])".toRegex()
                val matchResult = regexpPattern.find(it.sec_name)
                val value: String? = matchResult?.value
                value ?: ""
            },
            secId = it.secid,
            bondName = it.sec_name,
            couponPercent = it.coupon_percent,
            prevPrice = it.prev_price,
            maturityDate = it.maturity_date
        )
    }
}