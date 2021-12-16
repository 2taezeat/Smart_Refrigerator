package wikibook.learnandroid.smart_refrigerator.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NotificationInfo::class], version = 1)
abstract class NotificationInfoDatabase: RoomDatabase() {
        abstract fun notificationInfoDao(): NotificationInfoDao

        companion object {
                private var instance: NotificationInfoDatabase? = null

                @Synchronized
                fun getInstance(context: Context): NotificationInfoDatabase? {
                        if (instance == null) {
                                synchronized(NotificationInfoDatabase::class){
                                        instance = Room.databaseBuilder(
                                                context.applicationContext,
                                                NotificationInfoDatabase::class.java,
                                                "notificationInfo-database"
                                        ).build()
                                }
                        }
                        return instance
                }
        }
}