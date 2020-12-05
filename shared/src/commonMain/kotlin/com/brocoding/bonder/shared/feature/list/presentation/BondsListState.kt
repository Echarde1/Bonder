package com.brocoding.bonder.shared.feature.list.presentation

import com.brocoding.bonder.shared.data.dto.ListBondDTO
import com.brocoding.bonder.shared.feature.list.BondListEntity

sealed class BondsListState {

    object Loading : BondsListState()

    class Error(
        val th: Throwable
    ) : BondsListState()

    class Success(
        val result: List<BondListEntity>
    ) : BondsListState()

}

data class SillyState(
    val result: List<ListBondDTO> = emptyList(),
    val err: Throwable? = null,
    val loading: Boolean = false
)