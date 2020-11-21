/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.brocoding.bonder.shared.viewmodel

import androidx.lifecycle.ViewModel
import com.brocoding.bonder.shared.mainDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

actual open class ViewModel actual constructor() : ViewModel() {
    // for now dispatcher fixed on Main. after implementing multithread coroutines on native - we can change it
    protected actual val viewModelScope: CoroutineScope = CoroutineScope(mainDispatcher)

    public actual override fun onCleared() {
        super.onCleared()

        viewModelScope.cancel()
    }
}
