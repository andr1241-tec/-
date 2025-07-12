package student.testing.system.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseDTO(
    @SerializedName("name") val name: String = "",
    @SerializedName("id") val id: Int = 0,
    @SerializedName("img") val img: String = "",
    @SerializedName("course_code") val courseCode: String = "",
    @SerializedName("participants") val participantsDTO: List<ParticipantDTO> = listOf(),
) : Parcelable