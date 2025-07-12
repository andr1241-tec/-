package student.testing.system.presentation.ui.screens.resultsReview

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import student.testing.system.domain.models.ParticipantsResults
import student.testing.system.domain.states.loadableData.LoadableData
import student.testing.system.presentation.ui.components.CenteredColumn
import student.testing.system.presentation.ui.components.ErrorScreen

@Composable
fun UIReactionOnResultsListState(
    loadableData: LoadableData<ParticipantsResults>,
    onRetry: () -> Unit,
    @StringRes emptyListText: Int,
    onHideList: (Boolean) -> Unit
) {
    when (loadableData) {
        is LoadableData.Empty204 -> onHideList(true)
        is LoadableData.Error -> {
            onHideList(true)
            ErrorScreen(message = loadableData.exception, onRetry = onRetry)
        }

        is LoadableData.Loading -> onHideList(false)
        is LoadableData.NoState -> onHideList(false)
        is LoadableData.Success -> {
            if (loadableData.data.results.isEmpty()) {
                onHideList(true)
                CenteredColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                ) {
                    Text(
                        text = stringResource(emptyListText),
                        textAlign = TextAlign.Center,
                        color = Color(0xFF686868)
                    )
                }
            } else {
                onHideList(false)
            }
        }
    }
}