package com.niraj.tagnote.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niraj.tagnote.DB.entities.Note
import com.niraj.tagnote.DB.entities.NoteTagCrossRef
import com.niraj.tagnote.DB.entities.Tag

@Database(
    entities = [Note::class, Tag::class, NoteTagCrossRef::class],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {
    abstract val notesDao: notesDao
}