
import androidx.constraintlayout.widget.Group
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Group::class,
            parentColumns = ["gruop_id"],
            childColumns = ["group"]
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_student"]
        )
    ]
)
data class StudentGroup(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var group:Int,
    var user_student:Int
)