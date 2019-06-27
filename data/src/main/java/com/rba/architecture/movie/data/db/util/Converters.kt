package com.rba.architecture.movie.data.db.util

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun timestampToDate(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}