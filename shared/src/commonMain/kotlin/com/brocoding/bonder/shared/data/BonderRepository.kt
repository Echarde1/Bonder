package com.brocoding.bonder.shared.data

import com.brocoding.bonder.shared.Response
import com.brocoding.bonder.shared.data.dto.DetailsBondDTO
import com.brocoding.bonder.shared.data.dto.ListBondDTO
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class BonderRepository(
    private val httpClient: HttpClient
) {

    private companion object {

        val BASE_URL = "https://inv-realtime-server.herokuapp.com"

    }

    suspend fun getBondsList(): Response<List<ListBondDTO>> = tryCatch {
        val url = "$BASE_URL/bonds"
        val json = httpClient.get<String>(url)
        Json.decodeFromString(ListSerializer(ListBondDTO.serializer()), json)
    }

    suspend fun getBondDetails(secId: String): Response<DetailsBondDTO> = tryCatch {
        val url = "$BASE_URL/bonds/details"
        val json = httpClient.get<String>(
            url, { parameter("secid", secId) }
        )

        Json.decodeFromString(DetailsBondDTO.serializer(), json)
    }

    private suspend fun <T> tryCatch(func: suspend () -> T): Response<T> =
        try {
            val result = func.invoke()
            Response.Success(result)
        } catch (ex: Exception) {
            Response.Error(ex)
        }
}
