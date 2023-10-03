package com.niraj.tagnote.handler
import com.niraj.tagnote.DB.NoteWithTags
import com.niraj.tagnote.DB.entities.Tag

data class NotesState(

    // UI
    val notes: List<NoteWithTags> = emptyList(),
    val dialogOpen: Boolean = false,

    // Input
    val noteTitle: String = "",
    val noteText: String = "",
    val tags: List<Tag> = emptyList(),
    val tagsEditorText: String = ""
 )
