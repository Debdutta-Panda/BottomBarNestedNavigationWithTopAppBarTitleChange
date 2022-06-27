package com.debduttapanda.bottombarnestednavigationwithtopappbartitlechange

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Profile : Screen("profile", R.string.profile)
    object FriendsList : Screen("friendslist", R.string.friends_list)
}
val items = listOf(
    Screen.FriendsList,
    Screen.Profile,
)

fun getIcon(screen: Screen): ImageVector {
    return when(screen.route){
        "profile"-> Icons.Filled.Person
        "friendslist"-> Icons.Filled.Group
        else-> Icons.Filled.Favorite
    }
}