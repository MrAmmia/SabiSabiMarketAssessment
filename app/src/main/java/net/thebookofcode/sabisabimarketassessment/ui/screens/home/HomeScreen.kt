package net.thebookofcode.sabisabimarketassessment.ui.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import net.thebookofcode.sabisabimarketassessment.logic.navigation.NavigationItem.*
import net.thebookofcode.sabisabimarketassessment.logic.navigation.graphs.HomeNavGraph
import net.thebookofcode.sabisabimarketassessment.ui.components.AppBar
import net.thebookofcode.sabisabimarketassessment.ui.components.AppNavRail
import net.thebookofcode.sabisabimarketassessment.ui.components.Drawer
import net.thebookofcode.sabisabimarketassessment.ui.theme.SabiSabiMarketAssessmentTheme

@Composable
fun HomeScreen(
    widthSizeClass: WindowWidthSizeClass
) {
    SabiSabiMarketAssessmentTheme {
        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()
        val isDrawerActive = widthSizeClass == WindowWidthSizeClass.Compact
        val isExpanded = widthSizeClass == WindowWidthSizeClass.Expanded
        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpanded)
        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
        var selectedItemRoute by rememberSaveable { mutableStateOf(Shelf.route) }
        val destinations = listOf(
            Shelf,
            Invoice,
            Expense,
            Journal,
            Todo,
            Calendar
        )

        ModalNavigationDrawer(
            gesturesEnabled = !isExpanded,
            drawerState = sizeAwareDrawerState,
            drawerContent = {

                if (!isExpanded) {
                    Drawer(
                        shouldShowHeader = false,
                        destinations = destinations,
                        navigate = { navItem ->
                            selectedItemRoute = navItem.route
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                            coroutineScope.launch {
                                sizeAwareDrawerState.close()
                            }
                        },
                        selectedItemRoute = selectedItemRoute
                    )

                }
            }
        ) {
            if (!isExpanded) {
                Scaffold(
                    topBar = {
                        AppBar(
                            onNavigationItemClick = {
                                coroutineScope.launch {
                                    sizeAwareDrawerState.open()
                                }
                            },
                            drawerState = sizeAwareDrawerState
                        )
                    }
                )
                { innerPadding ->
                    Row(modifier = Modifier.padding(innerPadding)) {
                        HomeNavGraph(navController = navController)
                    }
                }
            } else {
                Row {
                    AppNavRail(
                        destinations = destinations,
                        navigate = { navItem ->
                            selectedItemRoute = navItem.route
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                            coroutineScope.launch {
                                sizeAwareDrawerState.close()
                            }
                        },
                        selectedItemRoute = selectedItemRoute
                    )

                    HomeNavGraph(navController = navController)
                }
            }
        }
    }
}


/**
 * Determine the drawer state to pass to the modal drawer.
 */
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = androidx.compose.material3.rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        DrawerState(DrawerValue.Closed)
    }
}
