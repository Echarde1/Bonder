package com.brocoding.bonder.shared

import kotlinx.coroutines.CoroutineDispatcher

expect val mainDispatcher: CoroutineDispatcher
expect val ioDispatcher: CoroutineDispatcher