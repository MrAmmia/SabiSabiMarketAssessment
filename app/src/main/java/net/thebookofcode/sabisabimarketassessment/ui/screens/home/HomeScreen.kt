package net.thebookofcode.sabisabimarketassessment.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.thebookofcode.sabisabimarketassessment.logic.navigation.NavigationItem.*
import net.thebookofcode.sabisabimarketassessment.logic.navigation.graphs.HomeNavGraph

@Composable
fun HomeScreen() {
    val navController: NavHostController = rememberNavController()

    /**
     * A list containing destinations as object of sealed class NavigationItem
     */
    val screens = listOf(Shelf, Invoice, Expense, Journal, Todo, Calendar)

    /**
     * selectedIndex remembers the index of the last selected tab
     * Default is 2 because that's the index of starting/home destination (Expense)
     */
    var selectedIndex by remember { mutableIntStateOf(2) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected =  selectedIndex == index ,
                        onClick = {
                            selectedIndex = index
                            // prevents unnecessary stacking of screens in the backstack
                            navController.navigate(route = screen.route){
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(imageVector = screen.icon, contentDescription = screen.title)
                        },
//                        label = {
//                            Text(text = screen.title)
//                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        HomeNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}
