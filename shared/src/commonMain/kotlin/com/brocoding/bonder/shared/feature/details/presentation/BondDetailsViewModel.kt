package com.brocoding.bonder.shared.feature.details.presentation

import com.brocoding.bonder.shared.CStateFlow
import com.brocoding.bonder.shared.asCommonFlow
import com.brocoding.bonder.shared.base.Response
import com.brocoding.bonder.shared.data.BonderApi
import com.brocoding.bonder.shared.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BondDetailsViewModel(
    private val secId: String,
    private val bonderApi: BonderApi
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
        when (val response = bonderApi.getBondDetails(secId)) {
            is Response.Error -> _state.value = BondDetailsState.Error(response.exception)
            is Response.Success -> _state.value = BondDetailsState.Success(response.data)
        }
    }
}