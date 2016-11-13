package ch.ocram.linkbasket.main.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.sql.Date

@Table(database = LinkDatabase::class)
class Link : BaseModel() {

    @Column
    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @Column
    var createdAt: Date = Date(System.currentTimeMillis())

    @Column
    var url : String = ""

    @Column
    var description: String = ""

}