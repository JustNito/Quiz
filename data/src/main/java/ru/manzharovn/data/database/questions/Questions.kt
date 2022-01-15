package ru.manzharovn.data.database.questions

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Questions (
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "question") val question: String,
    @NonNull @ColumnInfo(name = "answer") val answer: String,
    @ColumnInfo(name = "imgsrc") val imgSrc: String? = null,
)