package ui.screens.detail

import androidx.compose.runtime.Composable
import com.jmartinal.mynotes.ui.screens.detail.DetailViewModel
import org.jetbrains.compose.web.dom.Text

@Composable
fun Detail(
    viewModel: DetailViewModel,
    onClose: () -> Unit
) {
    Text("Detail")
}