package wikibook.learnandroid.smart_refrigerator.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotificationInfo(
        val notificationCategory : String,
        val kind : String,
        val location : String,
        val notificationBody : String,
        val notificationTime : String, // time
        val count : Int,
) {
        @PrimaryKey(autoGenerate = true) var id : Int = 0
}

