package com.jmartinal.mynotes

import androidx.compose.ui.window.ComposeUIViewController
import com.jmartinal.mynotes.ui.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}