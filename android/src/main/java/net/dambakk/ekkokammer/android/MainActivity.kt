package net.dambakk.ekkokammer.android

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.statusBarsHeight
import net.dambakk.ekkokammer.android.screens.Article
import net.dambakk.ekkokammer.android.screens.Dashboard
import net.dambakk.ekkokammer.android.screens.Topics
import net.dambakk.ekkokammer.android.theme.EkkoTheme
import net.dambakk.ekkokammer.android.theme.pink
import net.dambakk.ekkokammer.android.theme.primaryPurple
import org.koin.androidx.viewmodel.ext.android.viewModel


sealed class Screen(val route: String, @StringRes val id: Int, @DrawableRes val icon: Int? = null) {
    object Dashboard : Screen("dashboard", R.string.destination_dashboard, R.drawable.ic_group)
    object Topics : Screen("topics", R.string.destination_topics, R.drawable.ic_user)
    object Article : Screen("article/{articleUrl}", R.string.destination_article)
}

val bottomBarNavItems = listOf(
    Screen.Dashboard,
    Screen.Topics,
)


@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appViewModel: AppViewModel by viewModel()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()

            EkkoTheme {
                ProvideWindowInsets {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                backgroundColor = primaryPurple,
                                elevation = 0.dp,
                                modifier = Modifier
                                    .statusBarsHeight()
                                    .fillMaxWidth()
                            ) {}
                        },
                        bottomBar = { EkkoBottomNavigationBar(navController) },
                        bodyContent = { configureNavHost(navController, appViewModel) }
                    )
                }

            }
        }
    }

    @Composable
    private fun EkkoBottomNavigationBar(
        navController: NavHostController,
    ) {
        Column {
            BottomNavigation(
                backgroundColor = primaryPurple,
                elevation = 0.dp,
            ) {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.arguments?.getString(KEY_ROUTE)
                bottomBarNavItems.forEach { screen ->
                    val selected = currentRoute == screen.route
                    BottomNavigationItem(
                        icon = {
                            screen.icon?.let {
                                CoilImage(
                                    data = it,
                                    colorFilter = ColorFilter.tint(if (selected) Color.White else pink),
                                    modifier = Modifier.preferredSize(24.dp)
                                )
                            }
                        },
                        label = {
                            Text(
                                text = stringResource(id = screen.id),
                                color = if (selected) Color.White else pink,
                            )
                        },
                        selected = selected,
                        onClick = {
                            // Clear open fragment from backstack back to the start destination
                            navController.popBackStack(
                                navController.graph.startDestination,
                                false
                            )

                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        }
                    )
                }
            }
            Spacer(
                modifier = Modifier.fillMaxWidth()
                    .navigationBarsHeight()
                    .background(color = primaryPurple)
            )
        }
    }

    @ExperimentalAnimationApi
    @Composable
    private fun configureNavHost(
        navController: NavHostController,
        appViewModel: AppViewModel
    ) {
        NavHost(navController, startDestination = "dashboard") {
            composable(Screen.Dashboard.route) {
                Dashboard(
                    navController,
                    appViewModel
                )
            }
            composable(Screen.Topics.route) { Topics(appViewModel) }
            composable(Screen.Article.route) {
                Article(
                    it.arguments?.getString(
                        "articleUrl"
                    )
                )
            }
        }
    }
}
