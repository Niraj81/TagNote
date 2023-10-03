package com.niraj.tagnote.DB

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.niraj.tagnote.DB.entities.Note
import com.niraj.tagnote.DB.entities.NoteTagCrossRef
import com.niraj.tagnote.DB.entities.Tag

data class TagWithNotes(
    @Embedded val note: Tag,
    @Relation(
        parentColumn = "tag",
        entityColumn = "noteId",
        associateBy = Junction(NoteTagCrossRef::class)
    )
    val notes: List<Note>
)