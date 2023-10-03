package com.niraj.tagnote.handler

sealed interface NotesEvent {

    // UI
    object showDialog: NotesEvent
    object closeDialog: NotesEvent

    // Input
    data class setTitle(val title: String) : NotesEvent
    data class setText(val text: String) : NotesEvent
    data class setTags(val tagsEditorText: String) : NotesEvent
    object saveNote : NotesEvent
}
