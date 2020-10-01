package com.lowbottgames.reader.reddit.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lowbottgames.reader.reddit.kotlin.fragment.ItemFragment
import com.lowbottgames.reader.reddit.kotlin.fragment.MainFragment
import com.lowbottgames.reader.reddit.kotlin.model.Feed

class MainActivity : AppCompatActivity(),
        MainFragment.OnFeedEventsListener
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, MainFragment.newInstance())
                .commit()
        }
    }

    override fun onFeedClick(feed: Feed) {
        supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, ItemFragment.newInstance(feed))
            .addToBackStack(ItemFragment::class.java.simpleName)
            .commit()
    }

}