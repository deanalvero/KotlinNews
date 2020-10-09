package com.lowbottgames.reader.reddit.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lowbottgames.reader.reddit.kotlin.model.Feed
import com.lowbottgames.reader.reddit.kotlin.repository.FeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel(
    private val repository: FeedRepository
) : ViewModel() {

    private val _feed = MutableLiveData<List<Feed>>()

    val feed: LiveData<List<Feed>>
        get() = _feed

    fun loadFeed(name: String, isRefresh: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        val feed = repository.feed(name, isRefresh)
        if (feed != null) {
            _feed.postValue(feed)
        }
    }

}