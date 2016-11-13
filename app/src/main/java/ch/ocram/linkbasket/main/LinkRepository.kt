package ch.ocram.linkbasket.main

import com.raizlabs.android.dbflow.sql.language.Select

object LinkRepository {

    fun getAll(): MutableList<Link> {
        return Select()
                .from(Link::class.java)
                .where()
                .orderBy(Link_Table.description, false)
                .queryList()
    }
}