package com.venkata.org.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.venkata.org.common.ApiStateResponse
import com.venkata.org.model.dto.CountryDto
import com.venkata.org.viewmodel.CountryViewModel

/**
 * Composable screen to display a list of countries fetched from the ViewModel.
 * Observes [uiState] from the ViewModel and renders UI accordingly (Loading, Error, Success).
 */
@Composable
fun CountriesScreen(viewModel: CountryViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchAllCountries()
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        when(uiState){
            // Display error message if API call fails
            is ApiStateResponse.Error -> {
                Text((uiState as ApiStateResponse.Error).message)
            }
            // Show loading indicator during API calling
            ApiStateResponse.Loading -> {
                CircularProgressIndicator()
            }
            // Display country list on successful API response
            is ApiStateResponse.Success<List<CountryDto>> -> {
                val countries = (uiState as ApiStateResponse.Success<List<CountryDto>>).data

                LazyColumn {
                    items(countries){
                        CountryCardItemScreen(it)
                    }
                }
            }
        }

    }

}


/**
 * Displays an individual country as a card with its code, capital, and flag image.
 * @param country A [CountryDto] data to display
 */
@Composable
fun CountryCardItemScreen(country: CountryDto) {

    Card {
        Column {
            Text(country.code, style = MaterialTheme.typography.titleMedium)
            Text(country.capital, style = MaterialTheme.typography.bodySmall)
            AsyncImage(
                model = country.flag,
                contentDescription = "flag",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

        }
    }

}
