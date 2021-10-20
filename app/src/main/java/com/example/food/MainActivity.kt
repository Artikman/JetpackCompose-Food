package com.example.food

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.food.NavigationKeys.FOOD_CATEGORY_ID
import com.example.food.presentation.categories.FoodCategoriesViewState
import com.example.food.presentation.categories.FoodCategoriesScreen
import com.example.food.presentation.categories.FoodCategoriesViewModel
import com.example.food.presentation.category_details.FoodCategoryDetailsScreen
import com.example.food.presentation.category_details.FoodCategoryDetailsViewModel
import com.example.food.ui.theme.ComposeSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                FoodApp()
            }
        }
    }

}

@Composable
private fun FoodApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.FOOD_CATEGORIES_LIST) {
        composable(route = NavigationKeys.FOOD_CATEGORIES_LIST) {
            FoodCategoriesDestination(navController)
        }
        composable(
            route = NavigationKeys.FOOD_CATEGORY_DETAILS,
            arguments = listOf(navArgument(FOOD_CATEGORY_ID) {
                type = NavType.StringType
            })
        ) {
            FoodCategoryDetailsDestination()
        }
    }
}

@Composable
private fun FoodCategoriesDestination(navController: NavHostController) {
    val viewModel: FoodCategoriesViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    FoodCategoriesScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is FoodCategoriesViewState.Effect.Navigation.ToCategoryDetails) {
                navController.navigate("${NavigationKeys.FOOD_CATEGORIES_LIST}/${navigationEffect.categoryName}")
            }
        })
}

@Composable
private fun FoodCategoryDetailsDestination() {
    val viewModel: FoodCategoryDetailsViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    FoodCategoryDetailsScreen(state = state)
}

object NavigationKeys {
    const val FOOD_CATEGORY_ID = "foodCategoryName"
    const val FOOD_CATEGORIES_LIST = "food_categories_list"
    const val FOOD_CATEGORY_DETAILS = "$FOOD_CATEGORIES_LIST/{$FOOD_CATEGORY_ID}"
}