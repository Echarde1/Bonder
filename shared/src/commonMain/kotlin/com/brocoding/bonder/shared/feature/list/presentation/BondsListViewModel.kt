package com.brocoding.bonder.shared.feature.list.presentation

import com.brocoding.bonder.shared.data.dto.ShortBond
import com.brocoding.bonder.shared.base.Response
import com.brocoding.bonder.shared.service_locator.ServiceLocator
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BondsListViewModel : ViewModel() {

    private val _state = MutableStateFlow<BondsListState>(BondsListState.Loading)
    val state: StateFlow<BondsListState> = _state

    private val bonderApi by lazy { ServiceLocator.bonderApi }

    init {
        viewModelScope.launch {
            getBondsList()
        }
    }

    private suspend fun getBondsList() {
        when (val response = bonderApi.getBondsList()) {
            is Response.Error -> _state.value = BondsListState.Error(response.exception)
            is Response.Success -> _state.value = BondsListState.Success(
                listOf(
                    ShortBond(
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
            )
        }
    }
}