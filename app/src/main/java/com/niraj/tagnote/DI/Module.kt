package com.niraj.tagnote.DI

import android.content.Context
import androidx.room.Room
import com.niraj.tagnote.DB.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext app: Context
    ) : NotesDatabase {
        return Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            "notesDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun providesNotesDao(db: NotesDatabase) = db.notesDao
}