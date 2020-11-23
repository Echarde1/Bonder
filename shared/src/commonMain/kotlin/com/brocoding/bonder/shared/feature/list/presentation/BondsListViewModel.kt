package com.brocoding.bonder.shared.feature.list.presentation

import com.brocoding.bonder.shared.CStateFlow
import com.brocoding.bonder.shared.Response
import com.brocoding.bonder.shared.asCommonFlow
import com.brocoding.bonder.shared.data.BonderRepository
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
            is Response.Success -> _state.value = BondsListState.Success(response.data)
        }
    }
}