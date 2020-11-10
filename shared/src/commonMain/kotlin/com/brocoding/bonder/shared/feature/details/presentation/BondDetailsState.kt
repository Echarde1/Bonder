package com.brocoding.bonder.shared.feature.details.presentation

import com.brocoding.bonder.shared.data.dto.DetailsBond
import com.brocoding.bonder.shared.data.dto.ListBond
import com.brocoding.bonder.shared.feature.list.presentation.BondsListState

sealed class BondDetailsState {

    object Loading : BondDetailsState()

    class Error(
        val th: Throwable
    ) : BondDetailsState()

    class Success(
        val result: DetailsBond
    ) : BondDetailsState()

}