package com.jmartinal.mynotes.ui.components

import androidx.compose.runtime.*
import com.jmartinal.mynotes.data.Filter
import com.jmartinal.mynotes.data.Note
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import com.jmartinal.mynotes.ui.theme.AppStyleSheet

@Composable
fun FilterIconButton(
    onFilterClicked: (Filter) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Div {
        Div(attrs = { onClick { isExpanded = !isExpanded } }) {
            Icon(
                name = "search",
                attrs = { classes(AppStyleSheet.topBarIcon) }
            )
        }

        if (isExpanded)
            Div(attrs = { classes(AppStyleSheet.filtersActionExpanded) }) {
                listOf(
                    Filter.All to "All",
                    Filter.ByType(Note.Type.TEXT) to "Text",
                    Filter.ByType(Note.Type.AUDIO) to "Audio"
                ).forEach { (filter, text) ->
                    Div(
                        attrs = {
                            classes(AppStyleSheet.filtersActionExpandedItem)
                            onClick {
                                onFilterClicked(filter)
                                isExpanded = false
                            }
                        }
                    ) {
                        Text(text)
                    }
                }
            }
    }
}