package com.niraj.tagnote.DB.entities

import androidx.room.Entity

@Entity(primaryKeys = ["noteId", "tag"])
data class NoteTagCrossRef(
    val noteId: Long,
    val tag: String
)
