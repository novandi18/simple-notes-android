package com.novandi.mynotesapp.helper

import android.database.Cursor
import com.novandi.mynotesapp.db.DatabaseContract
import com.novandi.mynotesapp.entity.Note

object MappingHelper {
    fun mapCursorToArrayList(noteCursor: Cursor?): ArrayList<Note> {
        val notesList = ArrayList<Note>()

        noteCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE))
                val description = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE))
                notesList.add(Note(id, title, description, date))
            }
        }

        return notesList
    }
}