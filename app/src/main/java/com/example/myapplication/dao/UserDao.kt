
import androidx.room.*


@Dao
interface UsersDao {

    @Query("select * from user")
    fun getAllUsers(): List<User>

    @Insert
    fun addUser(user: User)

    @Delete
    fun deleteUser(u:User){}

    @Query("select * from user where user_id = :id")
    fun getUserById(id: Int): User?

    @Update
    fun setActive(user:User)

    @Query("select * from user " +
            "where login =:login and password =:password")
    fun login(login: String, password: String): User?

    @Query("select * from user " +
            "where login =:login")
    fun isLogin(login: String): User?
}