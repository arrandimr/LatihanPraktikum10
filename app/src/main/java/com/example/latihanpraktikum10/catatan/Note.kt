package com.example.latihanpraktikum10.catatan

import androidx.room.Entity
import androidx.room.PrimaryKey

//membuat roomdatabase
@Entity(tableName = "note_table")
data class Note(
    var title: String,
    var description: String,
    var referensi: String,
    var priority: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}