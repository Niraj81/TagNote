package com.niraj.tagnote.UIComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.niraj.tagnote.DB.NoteWithTags
import com.niraj.tagnote.handler.NotesEvent
import com.niraj.tagnote.handler.NotesState

@Composable
fun NoteComp (
    onEvent: (NotesEvent) -> Unit,
    noteWithTags: NoteWithTags,
    modifier: Modifier = Modifier
) {
    OutlinedCard (
        modifier = modifier.clickable {
            /* TODO */
        },
    ) {
        Text(
            text = noteWithTags.note.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(15.dp)
        )
        Text(
            text = noteWithTags.note.text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = noteWithTags.tags.toString(),
            modifier = Modifier.padding(15.dp)
        )
    }
}