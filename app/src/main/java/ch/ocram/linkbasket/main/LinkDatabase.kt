package ch.ocram.linkbasket.main

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = LinkDatabase.NAME, version = LinkDatabase.VERSION)
object LinkDatabase {
    const val NAME ="LinkDatabase"
    const val VERSION = 1
}