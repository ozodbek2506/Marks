package uz.itteacher.lessonroom.util.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.itteacher.lessonroom.util.entity.Group

@Dao
interface GroupDao {
    @Query("select * from user")
    fun getAllGroups(): List<Group>

    @Insert
    fun addGroup(group: Group)
}