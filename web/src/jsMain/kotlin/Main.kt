import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import ui.App
import ui.theme.AppStyleSheet

fun main() {
    composeSample()
    kotlinJsSample()
}

private fun composeSample() {
    document.getElementById("root") ?: return

    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)
        App()
    }
}

fun kotlinJsSample() {
    val message = document.getElementById("message") ?: return
    message.textContent = "Hello Kotlin/JS"

    val button = document.getElementById("button") ?: return
    button.addEventListener("click", {
        window.alert("You clicked a button!")
    })
}
