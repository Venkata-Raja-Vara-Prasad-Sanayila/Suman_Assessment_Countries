package com.venkata.org.model.repository

import com.venkata.org.common.ApiStateResponse
import com.venkata.org.model.dto.CountryDto
import com.venkata.org.model.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * Repository is the implementation of [IRepository] for data operations
 */
class Repository @Inject constructor(private val apiService: ApiService): IRepository {

    /**
     * Makes Network api call and fetches the data from the server
     * -emits all states of the network api call
     */
    override fun getAllCountres(): Flow<ApiStateResponse<List<CountryDto>>> = flow {

        val response = apiService.ge6tALlCountries()
        if(response.isSuccessful){
            response.body()?.let {
                emit(ApiStateResponse.Success(it))
            } ?: emit(ApiStateResponse.Error("Null Response"))
        }
        else{
            emit(ApiStateResponse.Error(response.errorBody().toString()))
        }

    }.catch {
        emit(ApiStateResponse.Error(it.localizedMessage))
    }
}