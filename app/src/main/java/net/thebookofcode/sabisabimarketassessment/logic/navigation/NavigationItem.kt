package net.thebookofcode.sabisabimarketassessment.logic.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Calendar : NavigationItem(
        route = "calendar",
        title = "CALENDAR",
        icon = Icons.Default.DateRange
    )

    object Expense : NavigationItem(
        route = "expense",
        title = "EXPENSE",
        icon = Icons.Default.ShoppingCart
    )

    object Invoice : NavigationItem(
        route = "invoice",
        title = "INVOICE",
        icon = Icons.Default.Menu
    )

    object Journal : NavigationItem(
        route = "journal",
        title = "JOURNAL",
        icon = Icons.Default.AccountBox
    )

    object Shelf : NavigationItem(
        route = "shelf",
        title = "SHELF",
        icon = Icons.Default.Info
    )

    object Todo : NavigationItem(
        route = "todo",
        title = "TODO",
        icon = Icons.AutoMirrored.Filled.List
    )
}