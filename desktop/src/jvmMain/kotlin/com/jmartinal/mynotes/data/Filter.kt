package com.jmartinal.mynotes.data

sealed interface Filter {
    object All: Filter
    class ByType(val type: Note.Type): Filter
}
