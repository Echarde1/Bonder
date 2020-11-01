package com.brocoding.bonder.androidApp.list

sealed class BondsListState {

    object Loading : BondsListState()

    object Error : BondsListState()

    object Success : BondsListState()

}