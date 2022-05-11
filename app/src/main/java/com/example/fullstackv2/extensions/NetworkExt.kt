package com.example.fullstackv2.extensions

import io.ktor.client.plugins.*

inline fun <reified T: Any> safeApi(block: () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (e: RedirectResponseException) {
        // 3xx - responses
        Result.failure(e)
    } catch (e: ClientRequestException) {
        // 4xx - responses
        Result.failure(e)
    } catch (e: ServerResponseException) {
        // 5xx - responses
        Result.failure(e)
    } catch (e: Exception) {
        Result.failure(e)
    }
}