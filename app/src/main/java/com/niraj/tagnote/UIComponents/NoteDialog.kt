package com.niraj.tagnote.UIComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niraj.tagnote.handler.NotesEvent
import com.niraj.tagnote.handler.NotesState
import javax.inject.Singleton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDialog (
    state: NotesState,
    onEvent: (NotesEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent (NotesEvent.closeDialog) },
        confirmButton = {
            Button(onClick = {
                onEvent(
                    NotesEvent.saveNote
                )
            }) {
                Text("Save Note")
            }
        },
        title = {
            Text("Add A Note!")
        },
        text = {
            Column {
                // NoteTitle
                TextField(
                    value = state.noteTitle,
                    onValueChange = {
                        onEvent(NotesEvent.setTitle(it))
                    },
                    singleLine = true,
                    supportingText = {
                        Text("Title")
                    }
                )
                Spacer(modifier = Modifier.height(5.dp))
                TextField(
                    value = state.noteText,
                    onValueChange = {
                        onEvent(NotesEvent.setText(it))
                    },
                    singleLine = true,
                    supportingText = {
                        Text("Text")
                    }
                )
                AnimatedVisibility(visible = state.tags.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        items(state.tags) {tag ->
                            Text(text = tag.tag)
                            Spacer(Modifier.width(3.dp))
                        }
                    }
                }
                TextField(
                    value = state.tagsEditorText,
                    onValueChange = {
                        onEvent(NotesEvent.setTags(it))
                    },
                    singleLine = true,
                    supportingText = {
                        Text("tags")
                    }
                )
            }
        },
    )
}