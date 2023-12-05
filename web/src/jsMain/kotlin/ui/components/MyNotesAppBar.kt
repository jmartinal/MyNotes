package ui.components

import androidx.compose.runtime.Composable
import com.jmartinal.mynotes.data.Filter
import com.jmartinal.mynotes.getAppTitle
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import ui.theme.AppStyleSheet

@Composable
fun MyNotesAppBar(
    onFilterClicked: (Filter) -> Unit,
) {
    Div(attrs = { classes(AppStyleSheet.topBar) }) {
        H1(attrs = { classes(AppStyleSheet.topBarTitle) }) {
            Text(getAppTitle())
        }
        FilterIconButton(onFilterClicked)
    }
}