/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.brocoding.bonder.shared.viewmodel

import com.brocoding.bonder.shared.mainDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlin.native.internal.GC

@ThreadLocal
private var isGCWorking = false

actual open class ViewModel actual constructor() {
    // for now dispatcher fixed on Main. after implementing multithread coroutines on native - we can change it
    protected actual val viewModelScope: CoroutineScope = CoroutineScope(mainDispatcher)

    actual open fun onCleared() {
        viewModelScope.cancel()
        // run Kotlin/Native GC
        if (!isGCWorking) {
            isGCWorking = true
            GC.collect()
            isGCWorking = false
        }
    }
}
