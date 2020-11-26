package net.dambakk.ekkokammer.android

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import dev.chrisbanes.accompanist.coil.CoilImage
import net.dambakk.ekkokammer.android.fragments.ArticleView
import net.dambakk.ekkokammer.android.fragments.Dashboard
import net.dambakk.ekkokammer.android.fragments.Topics
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


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appViewModel: AppViewModel by viewModel()

        setContent {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    BottomNavigation {
                        val backStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = backStackEntry?.arguments?.getString(KEY_ROUTE)
                        bottomBarNavItems.forEach { screen ->
                            BottomNavigationItem(
                                icon = {
                                    screen.icon?.let {
                                        CoilImage(
                                            data = it,
                                            modifier = Modifier.preferredSize(24.dp)
                                        )
                                    }
                                },
                                label = { Text(stringResource(id = screen.id)) },
                                selected = currentRoute == screen.route,
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
                }) {
                    NavHost(navController, startDestination = "dashboard") {
                        composable(Screen.Dashboard.route) {
                            Dashboard(
                                navController,
                                appViewModel
                            )
                        }
                        composable(Screen.Topics.route) { Topics() }
                        composable(Screen.Article.route) { ArticleView(it.arguments?.getString("articleUrl")) }
                    }
            }

        }

        // Going edge-to-edge
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
//                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

    }
}
