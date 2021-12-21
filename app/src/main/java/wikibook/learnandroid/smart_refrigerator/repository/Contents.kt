package wikibook.learnandroid.smart_refrigerator.repository

data class Contents(
        val kind : String,
        val location : String,
        val updateTime : String, // time
        val count : Int,
        val shelfTime : String, // data
        val purchaseDate : String, // data
        val image : String,
        val memo : String,
        val useAi : Boolean,
        val id : Long // nowTime
)

