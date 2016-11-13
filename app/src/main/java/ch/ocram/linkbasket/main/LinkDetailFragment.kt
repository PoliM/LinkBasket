package ch.ocram.linkbasket.main

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ch.ocram.linkbasket.main.model.Link
import ch.ocram.linkbasket.main.model.LinkRepository
import kotlinx.android.synthetic.main.activity_link_detail.*
import kotlinx.android.synthetic.main.link_detail.*

/**
 * A fragment representing a single Link detail screen.
 * This fragment is either contained in a [LinkListActivity]
 * in two-pane mode (on tablets) or a [LinkDetailActivity]
 * on handsets.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class LinkDetailFragment : Fragment() {

    /**
     * The link this fragment is presenting.
     */
    private var mItem: Link? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments.containsKey(ARG_ITEM_ID)) {
            val id = arguments.getLong(ARG_ITEM_ID)
            mItem = LinkRepository.getById(id)

            val appBarLayout = activity.findViewById(R.id.toolbar_layout) as CollapsingToolbarLayout
            appBarLayout.title = mItem!!.url
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.link_detail, container, false)

        if (mItem != null) {
            (rootView.findViewById(R.id.link_detail) as TextView).text = mItem!!.description
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        val ARG_ITEM_ID = "item_id"
    }
}
