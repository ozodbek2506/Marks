
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["subject_id"],
            childColumns = ["subject"]
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_teacher"]
        )
    ]
)
data class TeacherSubject(
    @PrimaryKey(autoGenerate = true) var id:Int =0,
    var subject:Int,
    var user_teacher:Int
)
