package ch.ocram.linkbasket.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import ch.ocram.linkbasket.main.model.Link
import ch.ocram.linkbasket.main.model.LinkDatabase
import kotlinx.android.synthetic.main.activity_new_link.*
import kotlinx.android.synthetic.main.content_new_link.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewLinkActivity : AppCompatActivity() {

    private var parentJob = Job()
    // By default all the coroutines launched in this scope should be using the Main dispatcher
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_link)
        setSupportActionBar(toolbar)

        val data = intent.data!!
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

        insert(link)

        this.finish()
    }

    fun insert(link: Link) = scope.launch(Dispatchers.IO) {
        LinkDatabase.getDatabase(applicationContext).linkDao().insert(link)
    }
}
