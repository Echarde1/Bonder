package com.brocoding.bonder.shared.feature.list.presentation

import com.brocoding.bonder.shared.data.dto.ShortBond

sealed class BondsListState {

    object Loading : BondsListState()

    data class Error(
        val th: Throwable
    ) : BondsListState()

    data class Success(
        val result: List<ShortBond>
    ) : BondsListState()

}