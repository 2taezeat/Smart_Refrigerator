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

        @Query("DELETE FROM NotificationInfo WHERE kind = :kind")
        fun deleteUserByKind(kind: String)
}
