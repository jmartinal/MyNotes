import androidx.compose.runtime.MutableState

fun <T> MutableState<T>.update(createNewValue: (T) -> T) {
    value = createNewValue.invoke(value)
}