package com.brocoding.bonder.shared.feature.details.presentation

import com.brocoding.bonder.shared.feature.details.BondDetailsEntity

sealed class BondDetailsState {

    object Loading : BondDetailsState()

    class Error(
        val th: Throwable
    ) : BondDetailsState()

    class Success(
        val result: BondDetailsEntity
    ) : BondDetailsState()

}