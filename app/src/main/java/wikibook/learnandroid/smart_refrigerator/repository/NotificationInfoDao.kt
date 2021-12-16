package wikibook.learnandroid.smart_refrigerator.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotificationInfoDao {
        @Query("SELECT * FROM NotificationInfo")
        fun getAll(): List<NotificationInfo>

        @Insert
        fun insert(user: NotificationInfo)

//        @Query("DELETE FROM User WHERE name = :name")
//        fun deleteUserByName(name: String)
}
