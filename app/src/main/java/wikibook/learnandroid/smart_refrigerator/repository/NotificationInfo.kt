package wikibook.learnandroid.smart_refrigerator.repository

import java.sql.Timestamp

data class NotificationInfo(
        val notificationCategory : String,
        val kind : String,
        val location : String,
        val notificationBody : String,
        val notificationTime : Timestamp, // time
        var count : Int,
)

