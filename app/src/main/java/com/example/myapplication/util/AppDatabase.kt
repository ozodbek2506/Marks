

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getUserDao(): UsersDao

    companion object{
        var instanse: AppDatabase? = null

        fun getInstanse(context: Context):AppDatabase{
            if (instanse== null){
                try{
                    instanse = Room.databaseBuilder(context,AppDatabase::class.java,"app_db")
                        .allowMainThreadQueries()
                        .build()
                }catch (e:Exception){
                    Log.e("NotesDatabase", "exception")
                }

            }
            return instanse!!
        }
    }
}