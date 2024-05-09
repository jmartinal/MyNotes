
import kotlinx.browser.document
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import ui.App
import ui.theme.AppStyleSheet

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
