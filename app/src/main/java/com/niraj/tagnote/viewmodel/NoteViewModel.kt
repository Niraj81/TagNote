package com.niraj.tagnote.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.niraj.tagnote.DB.NoteWithTags
import com.niraj.tagnote.DB.entities.Note
import com.niraj.tagnote.DB.entities.Tag
import com.niraj.tagnote.DB.notesRepository
import com.niraj.tagnote.handler.NotesEvent
import com.niraj.tagnote.handler.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val notesRepository: notesRepository
) : ViewModel() {

    private val _notesWithTags = notesRepository.getNoteWithTags().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(2000),
        emptyList()
    )
    val noteWithTags: StateFlow<List<NoteWithTags>>
        get() = _notesWithTags

    private val _state = MutableStateFlow(NotesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _notesWithTags.collect {
                _state.value = _state.value.copy(
                    notes = it
                )
            }
        }
    }
    fun onEvent(event: NotesEvent) {
        when(event) {
            NotesEvent.closeDialog -> {
                _state.update {
                    it.copy(
                        dialogOpen = false,
                        noteText = "",
                        noteTitle = "",
                        tags = emptyList()
                    )
                }
            }
            NotesEvent.showDialog -> {
                _state.update {
                    it.copy(
                        dialogOpen = true
                    )
                }
            }
            NotesEvent.saveNote -> {
                val noteWithTags = NoteWithTags(
                    note = Note(
                        title = _state.value.noteTitle,
                        text = _state.value.noteText,
                    ),
                    tags = _state.value.tags
                )
                viewModelScope.launch {
                    notesRepository.upsertNoteWithTags(noteWithTags)
                }
                _state.update {
                    it.copy(
                        dialogOpen = false,
                        noteTitle = "",
                        noteText = "",
                        tags = emptyList()
                    )
                }
            }
            is NotesEvent.setTags -> {
                val tagEditorText = event.tagsEditorText
                if(tagEditorText.endsWith(' ')) {
                    _state.update {
                        it.copy(
                            tags = it.tags.plus(Tag(tagEditorText.dropLast(1))),
                            tagsEditorText = ""
                        )
                    }

                } else {
                    _state.update {
                        it.copy(
                            tagsEditorText = event.tagsEditorText
                        )
                    }
                }
            }
            is NotesEvent.setText -> {
                _state.update {
                    it.copy(
                        noteText = event.text
                    )
                }
            }
            is NotesEvent.setTitle -> {
                _state.update {
                    it.copy(
                        noteTitle = event.title
                    )
                }
            }
        }
    }

}