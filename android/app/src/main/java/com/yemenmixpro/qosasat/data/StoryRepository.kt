package com.yemenmixpro.qosasat.data

class StoryRepository(private val dao: StoryDao) {
    fun all() = dao.all()
    suspend fun insert(story: Story) = dao.insert(story)
}
