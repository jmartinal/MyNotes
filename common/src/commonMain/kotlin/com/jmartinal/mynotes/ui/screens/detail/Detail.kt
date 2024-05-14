package com.jmartinal.mynotes.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jmartinal.mynotes.ui.viewmodels.DetailViewModel

data class DetailScreen(val noteId: Long) : Screen {

    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()
        val navigator = LocalNavigator.currentOrThrow
        Detail(
            viewModel = DetailViewModel(coroutineScope, noteId),
            onClose = { navigator.pop() }
        )
    }
}

@Composable
expect fun Detail(viewModel: DetailViewModel, onClose: () -> Unit)