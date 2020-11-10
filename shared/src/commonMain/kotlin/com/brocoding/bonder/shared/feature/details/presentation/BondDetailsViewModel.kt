package com.brocoding.bonder.shared.feature.details.presentation

import com.brocoding.bonder.shared.base.Response
import com.brocoding.bonder.shared.data.BonderApi
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
            is Response.Success -> _state.value = BondDetailsState.Success(response.data)
        }
    }
}