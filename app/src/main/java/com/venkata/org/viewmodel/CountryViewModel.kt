package com.venkata.org.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venkata.org.common.ApiStateResponse
import com.venkata.org.model.dto.CountryDto
import com.venkata.org.model.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * ViewModel responsible for fetching using [IRepository] and exposing data to the UI layer.
 */
@HiltViewModel
class CountryViewModel @Inject constructor(private val repo:IRepository): ViewModel() {

    //Backing property for [uiState]
    private val _uiState = MutableStateFlow<ApiStateResponse<List<CountryDto>>>(ApiStateResponse.Loading)
    //Immutable uiState expose to view component
    val uiState = _uiState.asStateFlow()

    /**
     * calls the [IRepository] fetch all countries function call and the result stores in the [_uiState]
     * handled the error cases and stores in th uiState in form of APiResponseState Error
     */
    fun fetchAllCountries(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.getAllCountres().collectLatest {
                    _uiState.value = it
                }
            }
            catch(e: Exception){
                _uiState.value = ApiStateResponse.Error(e.localizedMessage)
            }
        }
    }
}