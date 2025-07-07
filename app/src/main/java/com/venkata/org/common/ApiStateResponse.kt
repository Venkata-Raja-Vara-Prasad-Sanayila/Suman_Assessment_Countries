package com.venkata.org.common



/**
 * ApiResponseState is a sealed class that represents the different states of an API response.
 * - UI state based on network results in a clean and type-safe way.
 */
sealed class ApiStateResponse<out T> {
    data class Success<T>(val data: T): ApiStateResponse<T>()
    data class Error(val message: String): ApiStateResponse<Nothing>()
    data object Loading: ApiStateResponse<Nothing>()
}