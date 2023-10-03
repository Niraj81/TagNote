package com.niraj.tagnote.DB.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long ?= null,
    val title: String = "",
    val text: String = ""
)
