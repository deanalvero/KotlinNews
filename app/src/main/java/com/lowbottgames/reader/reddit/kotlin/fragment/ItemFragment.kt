package com.lowbottgames.reader.reddit.kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.lowbottgames.reader.reddit.kotlin.R
import com.lowbottgames.reader.reddit.kotlin.model.Feed
import com.lowbottgames.reader.reddit.kotlin.view.FeedParcelable

class ItemFragment : Fragment() {

    private val feed by lazy {
        arguments?.getParcelable<FeedParcelable>(KEY_FEED)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.title = feed?.title
        toolbar.subtitle = feed?.author
        toolbar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        val textViewBody: TextView = view.findViewById(R.id.textView_body)
        val scrollView: ScrollView = view.findViewById(R.id.scrollView)
        val webView: WebView = view.findViewById(R.id.webView)


        val text = feed?.text
        if (text.isNullOrEmpty()) {
            scrollView.visibility = View.GONE
            webView.visibility = View.VISIBLE
            webView.loadUrl(feed?.url)
        } else {
            textViewBody.text = text
            scrollView.visibility = View.VISIBLE
        }

    }

    companion object {
        const val KEY_FEED = "KEY_FEED"

        fun newInstance(feed: Feed) : Fragment {
            val fragment = ItemFragment()

            val feedParcelable = FeedParcelable(
                feed.title,
                feed.author,
                feed.thumbnail,
                feed.text,
                feed.url
            )

            val bundle = Bundle()
            bundle.putParcelable(KEY_FEED, feedParcelable)

            fragment.arguments = bundle
            return fragment
        }
    }
}