package ch.ocram.linkbasket.main.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LinkDao {

    @Query("SELECT * FROM links ORDER BY createdAt DESC")
    fun getAll(): LiveData<MutableList<Link>>

    @Query("SELECT * FROM links WHERE id=:id")
    fun getById(id: Long) : Link?

    @Insert
    fun insert(link: Link)
}