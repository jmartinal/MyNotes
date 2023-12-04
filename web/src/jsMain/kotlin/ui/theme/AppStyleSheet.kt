package ui.theme

import org.jetbrains.compose.web.css.*

object AppStyleSheet : StyleSheet() {

    val notesList by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        gap(8.px)
        width(100.percent)
        height(100.percent)
        alignItems(AlignItems.Center)
    }

    val noteCard by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        width(80.percent)
        maxWidth(600.px)
        padding(16.px)
        border(1.px, LineStyle.Solid, Color.gray)
        borderRadius(4.px)
        cursor("pointer")
    }

    val noteCardHeader by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Row)
        alignItems(AlignItems.Center)
    }

    val noteTitle by style {
        flex(1)
        padding(0.px)
        lineHeight(1.5.em)
        fontSize(20.px)
        fontWeight("normal")
    }


}