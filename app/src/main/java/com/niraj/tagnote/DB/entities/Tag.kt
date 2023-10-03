package com.niraj.tagnote.DB.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = false)
    val tag: String
)
