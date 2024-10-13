package net.thebookofcode.sabisabimarketassessment.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.thebookofcode.sabisabimarketassessment.logic.navigation.NavigationItem
import net.thebookofcode.sabisabimarketassessment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onNavigationItemClick: () -> Unit,
    drawerState: DrawerState
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            IconButton(onClick = onNavigationItemClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = if (drawerState.isOpen) {
                        stringResource(id = R.string.opened_drawer)
                    } else {
                        stringResource(id = R.string.closed_drawer)
                    }
                )
            }
        }
    )
}

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 60.sp)
    }
}

@Composable
fun Drawer(
    shouldShowHeader: Boolean,
    destinations: List<NavigationItem>,
    selectedItemRoute:String,
    navigate: (NavigationItem) -> Unit
) {
    ModalDrawerSheet {
        if (shouldShowHeader)
            DrawerHeader()
        Spacer(modifier = Modifier.height(16.dp))
        destinations.forEach { destination ->
            NavigationDrawerItem(
                label = { Text(text = destination.title) },
                selected = destination.route == selectedItemRoute,
                onClick = {
                    navigate(destination)
                },
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@Composable
fun AppNavRail(
    destinations: List<NavigationItem>,
    modifier: Modifier = Modifier,
    selectedItemRoute:String,
    navigate: (NavigationItem) -> Unit
) {
    NavigationRail(
        header = {

        },
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            NavigationRailItem(
                selected = destination.route == selectedItemRoute,
                onClick = {
                    navigate(destination)
                },
                icon = { Icon(destination.icon, destination.title) },
                label = { Text(text = destination.title) },
                alwaysShowLabel = true
            )

        }
    }
}
