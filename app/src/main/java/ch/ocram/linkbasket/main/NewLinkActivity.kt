package ch.ocram.linkbasket.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import ch.ocram.linkbasket.main.model.Link
import kotlinx.android.synthetic.main.activity_new_link.*
import kotlinx.android.synthetic.main.content_new_link.*

class NewLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_link)
        setSupportActionBar(toolbar)

        val data = intent.data
        editUri.setText(data.toString())
        editDescription.requestFocus()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.new_link_menu, menu)
        return true
    }

    fun onFinishLink(menuItem: MenuItem) {

        val link = Link()
        link.url = editUri.text.toString()
        link.description = editDescription.text.toString()

        link.save()

        this.finish()
    }
}
