package com.niraj.tagnote.DB

import androidx.room.Dao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class notesRepository @Inject constructor(
    private val notesDao: notesDao
) {

    suspend fun upsertNoteWithTags(noteWithTags: NoteWithTags) {
        notesDao.upsertNoteWithTags(noteWithTags)
    }

    fun getNoteWithTags() : Flow<List<NoteWithTags>> {
        return notesDao.getNoteWithTags()
    }

}