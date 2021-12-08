package wikibook.learnandroid.smart_refrigerator.repository

import java.sql.Timestamp

data class Contents(
        val kind : String,
        val location : String,
        val commentBody : String,
        val updateTime : Timestamp,
        var count : Int,
        val shelfTime : String,
        val purchaseDate : String,
        val image : String,
        val memo : String,
        val useAi : Boolean
)

