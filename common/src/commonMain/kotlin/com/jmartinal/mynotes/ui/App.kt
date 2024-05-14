package com.jmartinal.mynotes.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.jmartinal.mynotes.ui.screens.home.HomeScreen

@Composable
fun App() {
    Navigator(HomeScreen)
}
