package com.farzin.newdigikala.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.farzin.newdigikala.ui.screens.basket.IconWithBadge
import com.farzin.newdigikala.ui.theme.bottomBar
import com.farzin.newdigikala.ui.theme.selectedBottomBar
import com.farzin.newdigikala.ui.theme.unSelectedBottomBar
import com.farzin.newdigikala.util.Constants
import com.farzin.newdigikala.util.LocaleUtils
import com.farzin.newdigikala.viewmodel.BasketViewModel
import com.farzin.newdigikala.R

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
    viewModel: BasketViewModel = hiltViewModel()

) {
    LocaleUtils.setLocale(LocalContext.current, Constants.USER_LANGUAGE)

    val items = listOf(
        BottomNavItem(
            name = stringResource(id = R.string.home),
            route = Screen.Home.route,
            selectedIcon = painterResource(id = R.drawable.home_fill),
            deSelectedIcon = painterResource(id = R.drawable.home_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.category),
            route = Screen.Category.route,
            selectedIcon = painterResource(id = R.drawable.category_fill),
            deSelectedIcon = painterResource(id = R.drawable.category_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.basket),
            route = Screen.Basket.route,
            selectedIcon = painterResource(id = R.drawable.cart_fill),
            deSelectedIcon = painterResource(id = R.drawable.cart_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.my_digikala),
            route = Screen.Profile.route,
            selectedIcon = painterResource(id = R.drawable.user_fill),
            deSelectedIcon = painterResource(id = R.drawable.user_outline)
        ),

        )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry.value?.destination?.route in items.map { it.route }

    if (showBottomBar) {
        BottomNavigation(
            modifier = Modifier.height(60.dp),
            backgroundColor = MaterialTheme.colors.bottomBar,
            elevation = 5.dp
        ) {
            val cartCounter by viewModel.currentCartItemsCount.collectAsState(0)
            items.forEachIndexed { index, item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = MaterialTheme.colors.selectedBottomBar,
                    unselectedContentColor = MaterialTheme.colors.unSelectedBottomBar,
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (selected) {
                                if (index == 2 && cartCounter > 0) {
                                    IconWithBadge(
                                       cartCounter = cartCounter,
                                       icon =  item.selectedIcon
                                    )
                                } else {
                                    Icon(
                                        modifier = Modifier.height(24.dp),
                                        painter = item.selectedIcon,
                                        contentDescription = item.name
                                    )
                                }

                            } else {
                                if (index == 2 && cartCounter > 0) {
                                    IconWithBadge(
                                        cartCounter = cartCounter,
                                        icon = item.deSelectedIcon
                                    )
                                } else {
                                    Icon(
                                        modifier = Modifier.height(24.dp),
                                        painter = item.deSelectedIcon,
                                        contentDescription = item.name
                                    )
                                }

                            }
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    }
                )
            }
        }
    }

}