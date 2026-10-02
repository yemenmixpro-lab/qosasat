package com.yemenmixpro.qosasat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yemenmixpro.qosasat.data.Story
import com.yemenmixpro.qosasat.data.StoryRepository
import com.yemenmixpro.qosasat.QosasatApp
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StoryViewModel : ViewModel() {
    private val repo: StoryRepository
    val stories = MutableStateFlow<List<Story>>(emptyList())

    init {
        val dao = QosasatApp.database.storyDao()
        repo = StoryRepository(dao)
        viewModelScope.launch {
            dao.all().collect { list ->
                stories.value = list
            }
        }
    }

    fun insert(text: String) {
        viewModelScope.launch {
            repo.insert(Story(text = text))
        }
    }
}
