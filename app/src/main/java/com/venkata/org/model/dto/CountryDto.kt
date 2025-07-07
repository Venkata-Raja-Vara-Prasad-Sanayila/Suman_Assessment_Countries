package com.venkata.org.model.dto


/**
 * Data transfer object(DTO)
 * represents the type of response received from the fetch countries Api
 *
 */
data class CountryDto(
    val capital: String,
    val code: String,
    val flag: String,
)
