package com.debduttapanda.bottombarnestednavigationwithtopappbartitlechange

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    vm: HomeViewModel = viewModel()
) {
    val owner = LocalLifecycleOwner.current
    val context = LocalContext.current
    LaunchedEffect(key1 = vm.uiScope.value){
        vm.uiScope.forward(
            ScopeManager(
                navHostController = navController,
                owner = owner,
                toaster = Toaster(context)
            )
        )
    }
    val navController1 = rememberAnimatedNavController()
    var header by remember { mutableStateOf(Screen.FriendsList.route) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(header.uppercase())
                },
                backgroundColor = MaterialTheme.colors.primary,
            )
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color(0xfff44336),
                contentColor = Color.White
            ) {
                val navBackStackEntry by navController1.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(getIcon(screen), contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            header = screen.route
                            navController1.navigate(screen.route) {
                                popUpTo(navController1.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {innerPadding ->
        AnimatedNavHost(navController1, startDestination = Screen.Profile.route, Modifier.padding(innerPadding)) {
            composable(
                Screen.FriendsList.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                popEnterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                },
                popExitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                }
            ) { FriendList() }
            composable(
                Screen.Profile.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                },
                popEnterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                },
                popExitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                }
            ) { Profile() }
        }
    }
}