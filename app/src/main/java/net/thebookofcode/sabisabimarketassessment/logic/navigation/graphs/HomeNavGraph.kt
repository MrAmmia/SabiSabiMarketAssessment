package net.thebookofcode.sabisabimarketassessment.logic.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.thebookofcode.sabisabimarketassessment.logic.navigation.NavigationItem.*
import net.thebookofcode.sabisabimarketassessment.ui.screens.home.CalendarScreen
import net.thebookofcode.sabisabimarketassessment.ui.screens.home.ExpenseScreen
import net.thebookofcode.sabisabimarketassessment.ui.screens.home.InvoiceScreen
import net.thebookofcode.sabisabimarketassessment.ui.screens.home.JournalScreen
import net.thebookofcode.sabisabimarketassessment.ui.screens.home.ShelfScreen
import net.thebookofcode.sabisabimarketassessment.ui.screens.home.TodoScreen

@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Expense.route
    ) {
        composable(
            route = Shelf.route
        ) {
            ShelfScreen()
        }
        composable(
            route = Invoice.route
        ) {
            InvoiceScreen()
        }
        composable(
            route = Expense.route
        ) {
            ExpenseScreen()
        }
        composable(
            route = Journal.route
        ) {
            JournalScreen()
        }
        composable(
            route = Todo.route
        ) {
            TodoScreen()
        }
        composable(
            route = Calendar.route
        ) {
            CalendarScreen()
        }
    }
}