package com.brocoding.bonder.shared.feature.list.presentation

import com.brocoding.bonder.shared.CStateFlow
import com.brocoding.bonder.shared.asCommonFlow
import com.brocoding.bonder.shared.base.Response
import com.brocoding.bonder.shared.data.BonderApi
import com.brocoding.bonder.shared.service_locator.ServiceLocator
import com.brocoding.bonder.shared.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BondsListViewModel(
    private val bonderApi: BonderApi = ServiceLocator.bonderApi
) : ViewModel() {

    private val _state: MutableStateFlow<BondsListState> = MutableStateFlow(BondsListState.Loading)
    val state: CStateFlow<BondsListState> =  _state.asCommonFlow()

    init {
        viewModelScope.launch {
            getBondsList()
        }
    }

    private suspend fun getBondsList() {
        when (val response = bonderApi.getBondsList()) {
            is Response.Error -> _state.value = BondsListState.Error(response.exception)
            is Response.Success -> _state.value = BondsListState.Success(response.data)
        }
    }
}