package com.brocoding.bonder.shared.feature.list.presentation

import com.brocoding.bonder.shared.data.dto.ListBond

sealed class BondsListState {

    object Loading : BondsListState()

    class Error(
        val th: Throwable
    ) : BondsListState()

    class Success(
        val result: List<ListBond>
    ) : BondsListState()

}