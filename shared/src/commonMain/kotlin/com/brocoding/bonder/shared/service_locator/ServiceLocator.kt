package com.brocoding.bonder.shared.service_locator

import com.brocoding.bonder.shared.data.BonderApi
import io.ktor.client.*

object ServiceLocator {

    val bonderApi by lazy { BonderApi(httpClient) }

    private val httpClient by lazy { HttpClient() }

}