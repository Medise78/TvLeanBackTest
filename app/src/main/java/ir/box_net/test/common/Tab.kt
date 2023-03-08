package ir.box_net.test.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Tab(val routes: String, val name: String, val icon: ImageVector) {
    object Home : Tab("home_screen", "خانه", Icons.Default.Home)
    object Search:Tab("search_Screen" , "جستجو" , Icons.Default.Search)
}