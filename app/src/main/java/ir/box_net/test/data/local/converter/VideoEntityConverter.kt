package ir.box_net.test.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import ir.box_net.test.common.JsonParse
import ir.box_net.test.data.model.entity.VideoEntity

@ProvidedTypeConverter
class VideoEntityConverter(
    private val jsonParser: JsonParse
) {
    @TypeConverter
    fun fromTvVideosJson(json: String): List<VideoEntity> {
        return jsonParser.fromJson<ArrayList<VideoEntity>>(
            json,
            object : TypeToken<ArrayList<VideoEntity>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toTvVideosJson(videos: List<VideoEntity>): String {
        return jsonParser.toJson(
            videos,
            object : TypeToken<ArrayList<VideoEntity>>() {}.type
        ) ?: "[]"
    }
}