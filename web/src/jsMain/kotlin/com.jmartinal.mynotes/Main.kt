package com.jmartinal.mynotes

import com.jmartinal.mynotes.ui.App
import com.jmartinal.mynotes.ui.theme.AppStyleSheet
import kotlinx.browser.document
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
    composeSample()
}

private fun composeSample() {
    document.getElementById("root") ?: return

    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)
        App()
    }
}
