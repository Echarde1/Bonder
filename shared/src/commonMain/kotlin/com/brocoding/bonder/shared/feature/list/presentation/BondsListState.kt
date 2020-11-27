package com.brocoding.bonder.shared.feature.list.presentation

import com.brocoding.bonder.shared.data.dto.ListBond

sealed class BondsListState {

    object Loading : BondsListState()

    class Error(
        val th: Throwable
    ) : BondsListState()

    class Success(
        val result: List<BondListModel>
    ) : BondsListState()

}

data class SillyState(
    val result: List<ListBond> = emptyList(),
    val err: Throwable? = null,
    val loading: Boolean = false
)