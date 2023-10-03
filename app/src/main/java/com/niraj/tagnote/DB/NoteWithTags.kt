package com.niraj.tagnote.DB

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.niraj.tagnote.DB.entities.Note
import com.niraj.tagnote.DB.entities.NoteTagCrossRef
import com.niraj.tagnote.DB.entities.Tag

data class NoteWithTags(
    @Embedded val note: Note,
    @Relation(
        parentColumn = "noteId",
        entityColumn = "tag",
        associateBy = Junction(NoteTagCrossRef::class)
    )
    val tags: List<Tag>
)
