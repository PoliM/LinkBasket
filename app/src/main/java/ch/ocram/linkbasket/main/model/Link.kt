package ch.ocram.linkbasket.main.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "links")
data class Link(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var createdAt: Date = Date(System.currentTimeMillis()),

    var url : String = "",

    var description: String = ""
)
