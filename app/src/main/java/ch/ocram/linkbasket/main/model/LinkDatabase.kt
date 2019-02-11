package ch.ocram.linkbasket.main.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context


@Database(entities = arrayOf(Link::class), version = 1)
@TypeConverters(Converters::class)
abstract class LinkDatabase : RoomDatabase() {
    abstract fun linkDao(): LinkDao

    companion object {
        @Volatile
        private var INSTANCE: LinkDatabase? = null

        fun getDatabase(context: Context): LinkDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, LinkDatabase::class.java, "LinkDatabase").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}