package com.islam.core.navigation

import androidx.navigation.NavController

abstract class BaseNavigator {

    var navController: NavController? = null

    fun bind(navController: NavController) {
        this.navController = navController
    }

    fun unbind() {
        navController = null
    }
}