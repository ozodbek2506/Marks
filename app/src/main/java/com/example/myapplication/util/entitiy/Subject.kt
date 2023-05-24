
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = true) var subject_id: Int =0,
    var Subject_name: String
)