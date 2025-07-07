package com.venkata.org.model.repository

import com.venkata.org.common.ApiStateResponse
import com.venkata.org.model.dto.CountryDto
import kotlinx.coroutines.flow.Flow


/**
 * [IRepository] it acts as abstraction layer to provide data operation functionalities to other components
 */
interface IRepository {

    /**
     * It fetches the list of countries and
     * @return emitted data from [ApiStateResponse] states with [List] of [CountryDto]
     */
    fun getAllCountres(): Flow<ApiStateResponse<List<CountryDto>>>
}