package com.example.food.presentation.category_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food.presentation.categories.FoodItemRow

@Composable
fun FoodCategoryDetailsScreen(state: FoodCategoryDetailsViewState.State) {
    val scrollState = rememberLazyListState()
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Spacer(modifier = Modifier.height(2.dp))
            LazyColumn(
                state = scrollState,
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(state.categoryFoodItems) { item ->
                    FoodItemRow(
                        item = item
                    )
                }
            }
        }
    }
}