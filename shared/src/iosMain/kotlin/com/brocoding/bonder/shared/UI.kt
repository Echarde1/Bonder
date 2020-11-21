/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.brocoding.bonder.shared

import com.brocoding.bonder.shared.viewmodel.UIDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/*actual val mainDispatcher: CoroutineDispatcher
    get() = UIDispatcher()*/
actual val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
actual val ioDispatcher: CoroutineDispatcher = Dispatchers.Main