    package com.niraj.tagnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.niraj.tagnote.UIComponents.NoteComp
import com.niraj.tagnote.UIComponents.NoteDialog
import com.niraj.tagnote.handler.NotesEvent
import com.niraj.tagnote.ui.theme.TagNoteTheme
import com.niraj.tagnote.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TagNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val viewModel: NoteViewModel = viewModel()
    val state by viewModel.state.collectAsState()
    val onEvent = viewModel::onEvent
    Surface (
        modifier = Modifier.fillMaxSize()
    ){
        if(state.dialogOpen) {
            NoteDialog(state = state, onEvent = onEvent)
        }
        Column {
            Button(onClick = {
                onEvent(NotesEvent.showDialog)
            }) {
                Text("Add Note")
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                    items(state.notes) {
                        NoteComp(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(6.dp),
                            onEvent = onEvent,
                            noteWithTags = it
                        )
                    }
                }
            )
        }
        Column {

        }
    }
}