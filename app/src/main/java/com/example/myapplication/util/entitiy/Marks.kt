
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
        entity = StudentGroup::class,
        parentColumns = ["id"],
        childColumns = ["student_group"]
    ),
    ForeignKey(
        entity = TeacherSubject::class,
        parentColumns = ["id"],
        childColumns = ["teacher_subject"]
    )
])
data class Marks(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var teacher_subject:Int,
    var student_group:Int
     )
