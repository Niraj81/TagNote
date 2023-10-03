package com.niraj.tagnote.DB

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.niraj.tagnote.DB.entities.Note
import com.niraj.tagnote.DB.entities.NoteTagCrossRef
import com.niraj.tagnote.DB.entities.Tag
import kotlinx.coroutines.flow.Flow

@Dao
interface notesDao {
    @Upsert
    suspend fun upsertNote(note: Note) : Long

    @Upsert
    suspend fun upsertTag(tag: Tag)

    @Upsert
    suspend fun upsertNoteTagCrossRef(crossRef: NoteTagCrossRef)

    @Transaction
    @Upsert
    suspend fun upsertNoteWithTags(notesWithTag: NoteWithTags) {
        val noteId = upsertNote(notesWithTag.note)
        notesWithTag.tags.forEach {tag ->
            upsertTag(tag)
            upsertNoteTagCrossRef(
                NoteTagCrossRef(
                    noteId,
                    tag.tag
                )
            )
        }
    }

    @Transaction
    @Query("SELECT * FROM note")
    fun getNoteWithTags() : Flow<List<NoteWithTags>>

}