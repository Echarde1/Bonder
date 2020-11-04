package com.brocoding.bonder.shared.data

import com.brocoding.bonder.shared.base.Response
import com.brocoding.bonder.shared.data.dto.ShortBond
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class BonderApi(
    private val httpClient: HttpClient
) {

    suspend fun getBondsList(): Response<List<ShortBond>> {
        return try {
            val url = "https://inv-realtime-server.herokuapp.com/bonds"
            val json = httpClient.get<String>(url)

            val response = Json.decodeFromString(ListSerializer(ShortBond.serializer()), json)
            Response.Success(response)
        } catch (ex: Exception) {
            Response.Error(ex)
        }
    }
}