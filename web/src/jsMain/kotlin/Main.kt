import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import ui.App
import ui.theme.AppStyleSheet

fun main() {

    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)

        App()
    }
}
