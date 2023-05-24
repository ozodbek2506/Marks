
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey(autoGenerate = true)
    var group_id: Int =0,
    var group_number: String,
    var class_number: Int,
)