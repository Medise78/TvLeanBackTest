package ir.box_net.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.box_net.test.common.Routes
import ir.box_net.test.common.Tab
import ir.box_net.test.presentation.detail_screen.DetailScreen
import ir.box_net.test.presentation.home_screen.HomeScreen
import ir.box_net.test.presentation.home_screen.component.MenuDrawer
import ir.box_net.test.ui.theme.IrTheme
import test.box_net.ir.R

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IrTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    val navController = rememberAnimatedNavController()

                    CompositionLocalProvider(LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Rtl) {

                        val tabs = listOf(Tab.Home, Tab.Search)
                        val currentBackStack by navController.currentBackStackEntryAsState()
                        val currentDestination = currentBackStack?.destination
                        val currentTab =
                            tabs.find { it.routes == currentDestination?.route } ?: Tab.Home

                        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                            val (logo) = createRefs()

                            Icon(
                                painter = painterResource(id = R.mipmap.ic_launcher),
                                contentDescription = getString(R.string.logo),
                                modifier = Modifier.constrainAs(logo) {
                                    end.linkTo(parent.end, margin = 28.dp)
                                    top.linkTo(parent.top, margin = 28.dp)
                                }
                            )
                            Row(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                MenuDrawer(
                                    selectedTab = currentTab,
                                    tabs = tabs,
                                    onTabSelected = {
                                        navController.navigate(it.routes)
                                    },
                                    modifier = Modifier.padding(bottom = 40.dp)
                                )
                                AnimatedNavHost(
                                    navController = navController,
                                    startDestination = Routes.HomeScreen.routes
                                ) {
                                    composable(
                                        Routes.HomeScreen.routes,
                                        enterTransition = { null },
                                        exitTransition = { null }
                                    ) {
                                        HomeScreen(navController = navController)
                                    }
                                    composable(
                                        Routes.DetailScreen.routes + "/{id}",
                                        arguments = listOf(
                                            navArgument("id") {
                                                type = NavType.IntType
                                            }
                                        ),
                                        exitTransition = {
                                            null
                                        },
                                        enterTransition = {
                                            null
                                        }) {
                                        DetailScreen()
                                    }
                                    composable(
                                        Tab.Search.routes,
                                        enterTransition = { null },
                                        exitTransition = { null }
                                    ) {

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}