package com.brocoding.bonder.shared.service_locator

import com.brocoding.bonder.shared.data.BonderRepository
import io.ktor.client.*

object ServiceLocator {

    val bonderRepository by lazy { BonderRepository(httpClient) }

    private val httpClient by lazy { HttpClient() }

}