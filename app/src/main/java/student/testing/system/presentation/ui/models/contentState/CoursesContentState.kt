package student.testing.system.presentation.ui.models.contentState

import stdio.lilith.annotations.ContentState
import student.testing.system.domain.states.loadableData.LoadableData
import student.testing.system.domain.models.Course

@ContentState
data class CoursesContentState(
    val courses: LoadableData<List<Course>> = LoadableData.NoState,
    // TODO сделать CoursesState и перенести это туда как sealed interface, затем закинуть CoursesState в UIStateWrapper
    val isLoggedOut: Boolean = false
)