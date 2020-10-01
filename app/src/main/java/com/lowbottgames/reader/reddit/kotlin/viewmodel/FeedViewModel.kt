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

    private val _kotlinFeed = MutableLiveData<List<Feed>>()

    val kotlinFeed: LiveData<List<Feed>>
        get() = _kotlinFeed

    fun loadKotlinFeed(isRefresh: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        val feed = repository.kotlinFeed(isRefresh)
        if (feed != null) {
            _kotlinFeed.postValue(feed)
        }
    }

}