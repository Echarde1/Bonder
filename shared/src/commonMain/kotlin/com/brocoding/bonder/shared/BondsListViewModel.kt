package com.brocoding.bonder.shared

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BondsListViewModel : ViewModel() {

    private val _count = MutableStateFlow(0)
    val count : StateFlow<Int> = _count



}