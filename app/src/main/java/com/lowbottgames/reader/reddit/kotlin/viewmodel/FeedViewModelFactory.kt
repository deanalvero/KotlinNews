package com.lowbottgames.reader.reddit.kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lowbottgames.reader.reddit.kotlin.repository.FeedRepository
import java.lang.IllegalArgumentException

class FeedViewModelFactory(
    private val repository: FeedRepository
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FeedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}