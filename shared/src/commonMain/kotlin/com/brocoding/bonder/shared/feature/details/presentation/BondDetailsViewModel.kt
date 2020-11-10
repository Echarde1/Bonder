package com.brocoding.bonder.shared.feature.details.presentation

import com.brocoding.bonder.shared.base.Response
import com.brocoding.bonder.shared.data.BonderApi
import com.brocoding.bonder.shared.data.dto.DetailsBond
import com.brocoding.bonder.shared.feature.ViewState
import com.brocoding.bonder.shared.service_locator.ServiceLocator
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BondDetailsViewModel(
    private val secId: String,
    private val bonderApi: BonderApi = ServiceLocator.bonderApi
) : ViewModel() {

    private val _state = MutableStateFlow<BondDetailsState>(BondDetailsState.Loading)
    val state: StateFlow<BondDetailsState> = _state

    init {
        viewModelScope.launch {
            getBondsDetails()
        }
    }

    private suspend fun getBondsDetails() {
        when (val response = bonderApi.getBondDetails(secId)) {
            is Response.Error -> _state.value = BondDetailsState.Error(response.exception)
            is Response.Success -> _state.value = BondDetailsState.Success(
                DetailsBond(
                    secid = "RU000A0JNYN1",
                    sec_name = "Гор.Обл.Займ Москвы 48 в.",
                    prev_price = 101.51,
                    offer_date = "",
                    maturity_date = "2022-06-11",
                    list_level = 1,
                    isin = "RU000A0JNYN1",
                    duration = 558,
                    coupon_value = "30.08",
                    coupon_percent = "6",
                    board_id = "TQCB",
                    accumulated_coupon_income = "23.67"
                )
            )
        }
    }
}