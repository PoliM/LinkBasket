package ch.ocram.linkbasket.main


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import ch.ocram.linkbasket.main.model.Link
import ch.ocram.linkbasket.main.model.LinkDatabase
import kotlinx.android.synthetic.main.activity_link_list.*


/**
 * An activity representing a list of Links. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [LinkDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class LinkListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false
    private val simpleItemRecyclerViewAdapter = SimpleItemRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener {
            sendMail()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.link_list)
        setupRecyclerView(recyclerView)

        if (findViewById<View>(R.id.link_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }
    }

    private fun sendMail() {

        val msg = StringBuilder()

        this.simpleItemRecyclerViewAdapter.getLinks().forEach { link ->
            msg.append(link.url).append('\n')
            msg.append(link.createdAt).append(" : ").append(link.description)
            msg.append("\n\n")
        }

        val emailApp = Intent(Intent.ACTION_SEND)
        emailApp.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("marco.a.poli@gmail.com"))
        emailApp.putExtra(Intent.EXTRA_SUBJECT, "Link Basket")
        emailApp.putExtra(Intent.EXTRA_TEXT, msg.toString())
        emailApp.type = "message/rfc822"
        startActivity(Intent.createChooser(emailApp, "Send Email Via"))
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        recyclerView.adapter = simpleItemRecyclerViewAdapter

        LinkDatabase.getDatabase(applicationContext).linkDao().getAll().observe(this, Observer { links ->
            links?.let { this.simpleItemRecyclerViewAdapter.setLinks(links) }
        })
    }

    inner class SimpleItemRecyclerViewAdapter() : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private var mValues: List<Link> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.link_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.mItem = mValues[position]
            holder.mIdView.text = mValues[position].url
            holder.mContentView.text = mValues[position].description

            holder.mView.setOnClickListener { v ->
                if (mTwoPane) {
                    val arguments = Bundle()
                    arguments.putLong(LinkDetailFragment.ARG_ITEM_ID, holder.mItem!!.id)
                    val fragment = LinkDetailFragment()
                    fragment.arguments = arguments
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.link_detail_container, fragment)
                            .commit()
                } else {
                    val context = v.context
                    val intent = Intent(context, LinkDetailActivity::class.java)
                    intent.putExtra(LinkDetailFragment.ARG_ITEM_ID, holder.mItem!!.id)

                    context.startActivity(intent)
                }
            }
        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        internal fun setLinks(links: List<Link>) {
            this.mValues = links
            notifyDataSetChanged()
        }

        internal fun getLinks(): List<Link> {
            return this.mValues
        }

        inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            val mIdView: TextView
            val mContentView: TextView
            var mItem: Link? = null

            init {
                mIdView = mView.findViewById(R.id.id) as TextView
                mContentView = mView.findViewById(R.id.content) as TextView
            }

            override fun toString(): String {
                return super.toString() + " '" + mContentView.text + "'"
            }
        }
    }
}
