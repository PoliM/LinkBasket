package ch.ocram.linkbasket.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_link.*
import kotlinx.android.synthetic.main.content_new_link.*

class NewLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_link)

        setSupportActionBar(toolbar)

        fabSave.setOnClickListener { view ->
            finishLink()
        }

        val data = intent.data
        editUri.setText(data.toString())
        editDescription.requestFocus()
    }

    fun finishLink() {
        this.finish()
    }
}
