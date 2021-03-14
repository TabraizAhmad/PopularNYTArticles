package com.example.popularnyarticles.utils

import androidx.room.TypeConverter
import com.example.popularnyarticles.network.model.Media
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converters {

    companion object {
    var gson = Gson()
        @JvmStatic
        @TypeConverter
        fun stringToMediaList(data: String?): List<Media?>? {
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type = object : TypeToken<List<Media?>?>() {}.type
            return gson.fromJson<List<Media?>>(data, listType)
        }

        @JvmStatic
        @TypeConverter
        fun mediaListToString(mediaList: List<Media>?): String? {
            return gson.toJson(mediaList)
        }
    }

}

