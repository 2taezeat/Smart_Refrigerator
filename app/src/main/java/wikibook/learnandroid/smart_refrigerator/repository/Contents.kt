package wikibook.learnandroid.smart_refrigerator.repository

import java.sql.Timestamp

data class Contents(
        val kind : String,
        val location : String,
        val updateTime : Timestamp, // time
        val count : Int,
        val shelfTime : String, // data
        val purchaseDate : String, // data
        val image : String,
        val memo : String,
        val useAi : Boolean
)

