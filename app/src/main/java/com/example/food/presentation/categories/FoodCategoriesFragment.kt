package com.example.food.presentation.categories

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.food.domain.entity.FoodItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

const val LAUNCH_LISTEN_FOR_EFFECTS = "launch-listen-to-effects"

@Composable
fun FoodCategoriesScreen(
    state: FoodCategoriesViewState.State,
    effectFlow: Flow<FoodCategoriesViewState.Effect>?,
    onEventSent: (event: FoodCategoriesViewState.Event) -> Unit,
    onNavigationRequested: (navigationEffect: FoodCategoriesViewState.Effect.Navigation) -> Unit
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is FoodCategoriesViewState.Effect.Navigation.ToCategoryDetails -> onNavigationRequested(
                    effect
                )
            }
        }?.collect()
    }

    Box {
        FoodCategoriesList(foodItems = state.categories) { itemId ->
            onEventSent(FoodCategoriesViewState.Event.CategorySelection(itemId))
        }
        if (state.isLoading)
            LoadingBar()
    }
}

@Composable
fun FoodCategoriesList(
    foodItems: List<FoodItem>,
    onItemClicked: (id: String) -> Unit = { }
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(foodItems) { item ->
            FoodItemRow(item = item, onItemClicked = onItemClicked)
        }
    }
}

@Composable
fun FoodItemRow(
    item: FoodItem,
    onItemClicked: (id: String) -> Unit = { }
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable { onItemClicked(item.id) }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            FoodItemDetails(
                item = item,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 24.dp,
                        bottom = 24.dp
                    )
                    .fillMaxWidth(0.80f)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun FoodItemDetails(
    item: FoodItem?,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = item?.name ?: "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        if (item?.description?.trim()?.isNotEmpty() == true)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = item.description.trim(),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.caption
                )
            }
    }
}

@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}