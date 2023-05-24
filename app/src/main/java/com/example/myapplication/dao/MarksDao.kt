package uz.itteacher.lessonroom.util.dao

import androidx.room.*
import uz.itteacher.lessonroom.util.entity.Marks

@Dao
interface MarksDao {

    @Query("select * from user")
    fun getAllMarks(): List<Marks>

    @Insert
    fun addMark(mark: Marks)

    @Delete
    fun deleteMark(mark: Marks)

    @Query("select * from user where user_id = :id")
    fun getMarkById(id: Int): Marks?

    @Update
    fun updateMark(mark:Marks)
}