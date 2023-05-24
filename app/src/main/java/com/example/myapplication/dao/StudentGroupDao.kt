package uz.itteacher.lessonroom.util.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.itteacher.lessonroom.util.entity.StudentGroup
import uz.itteacher.lessonroom.util.entity.User

@Dao
interface StudentGroupDao {
    @Query("select * from studentgroup")
    fun getAllStudentGroup(): List<StudentGroup>

    @Insert
    fun addStudentGroup(user: StudentGroup)
}