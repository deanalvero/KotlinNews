package com.lowbottgames.reader.reddit.kotlin.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lowbottgames.reader.reddit.kotlin.R
import com.lowbottgames.reader.reddit.kotlin.adapter.FeedAdapter
import com.lowbottgames.reader.reddit.kotlin.database.KNDatabase
import com.lowbottgames.reader.reddit.kotlin.model.Feed
import com.lowbottgames.reader.reddit.kotlin.network.ApiEndpoint
import com.lowbottgames.reader.reddit.kotlin.network.ServiceBuilder
import com.lowbottgames.reader.reddit.kotlin.repository.FeedRepositoryImpl
import com.lowbottgames.reader.reddit.kotlin.viewmodel.FeedViewModel
import com.lowbottgames.reader.reddit.kotlin.viewmodel.FeedViewModelFactory

class MainFragment : Fragment() {

    private lateinit var feedViewModel: FeedViewModel
    private var listener: OnFeedEventsListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.app_name)

        val service = ServiceBuilder.buildService(ApiEndpoint::class.java)
        val database = KNDatabase.getInstance(context!!.applicationContext)
        val repository = FeedRepositoryImpl(database, service)

        feedViewModel = ViewModelProvider(
            this,
            FeedViewModelFactory(repository)
        ).get(FeedViewModel::class.java)

        val adapter = FeedAdapter()
        adapter.listener = object : FeedAdapter.FeedAdapterListener {
            override fun onClickItem(feed: Feed) {
                listener?.onFeedClick(feed)
            }
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter

        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            feedViewModel.loadFeed("kotlin", true)
        }

        feedViewModel.feed.observe(this) {
            swipeRefreshLayout.isRefreshing = false

            adapter.items = it
            adapter.notifyDataSetChanged()
        }

        feedViewModel.loadFeed("kotlin", false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnFeedEventsListener
    }

    interface OnFeedEventsListener {
        fun onFeedClick(feed: Feed)
    }

    companion object {
        fun newInstance() : Fragment {
            return MainFragment()
        }
    }
}