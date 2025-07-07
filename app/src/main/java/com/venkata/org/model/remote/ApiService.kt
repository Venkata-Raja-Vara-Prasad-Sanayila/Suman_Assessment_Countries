package com.venkata.org.model.remote

import com.venkata.org.model.dto.CountryDto
import retrofit2.Response
import retrofit2.http.GET


/**
 * APi service for making the network requests
 */
interface ApiService {

    /**
     * Get request to fetch the list of countries
     * @return response [List] of [CountryDto] objects
     */
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun ge6tALlCountries():Response<List<CountryDto>>
}