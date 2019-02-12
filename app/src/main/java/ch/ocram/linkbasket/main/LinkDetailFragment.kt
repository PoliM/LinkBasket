package ch.ocram.linkbasket.main

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import ch.ocram.linkbasket.main.model.Link
import ch.ocram.linkbasket.main.model.LinkDatabase

/**
 * A fragment representing a single Link detail screen.
 * This fragment is either contained in a [LinkListActivity]
 * in two-pane mode (on tablets) or a [LinkDetailActivity]
 * on handsets.
 */
class LinkDetailFragment : Fragment() {

    /**
     * The link this fragment is presenting.
     */
    private var mItem: Link? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments?.containsKey(ARG_ITEM_ID) ?: false) {
            val appBarLayout = activity!!.findViewById(R.id.toolbar_layout) as CollapsingToolbarLayout
            val id = arguments!!.getLong(ARG_ITEM_ID)
            LinkDatabase.getDatabase(context!!).linkDao().getById(id).observe(this, Observer { link ->
                mItem = link
                appBarLayout.title = mItem!!.url
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.link_detail, container, false)

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
