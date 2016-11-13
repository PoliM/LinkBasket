package ch.ocram.linkbasket.main.model

import com.raizlabs.android.dbflow.sql.language.Select

object LinkRepository {

    fun getAll(): MutableList<Link> {
        return Select()
                .from(Link::class.java)
                .where()
                .orderBy(Link_Table.createdAt, false)
                .queryList()
    }

    fun getById(id: Long) : Link? {
        return Select().from(Link::class.java).where(Link_Table.id.eq(id)).querySingle();
    }
}