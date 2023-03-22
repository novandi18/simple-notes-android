package com.novandi.mynotesapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns._ID
import com.novandi.mynotesapp.db.DatabaseContract.NoteColumns
import java.sql.SQLException

class NoteHelper(context: Context) {
    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = NoteColumns.TABLE_NAME

        private var INSTANCE: NoteHelper? = null
        fun getInstance(context: Context): NoteHelper = INSTANCE ?: synchronized(this) {
            INSTANCE ?: NoteHelper(context)
        }
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()
        if (database.isOpen) database.close()
    }

    fun queryAll(): Cursor {
        return database.query(DATABASE_TABLE,
            null, null, null, null, null, "$_ID ASC")
    }

    fun queryById(id: String): Cursor {
        return database.query(DATABASE_TABLE,
            null, "$_ID = ?", arrayOf(id), null, null, null, null)
    }

    fun insert(values: ContentValues?): Long = database.insert(DATABASE_TABLE, null, values)

    fun update(id: String, values: ContentValues?): Int
        = database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))

    fun deleteById(id: String): Int = database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
}