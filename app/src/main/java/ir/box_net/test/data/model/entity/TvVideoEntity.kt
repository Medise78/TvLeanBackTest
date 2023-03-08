package ir.box_net.test.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ir.box_net.test.data.local.converter.VideoEntityConverter
import ir.box_net.test.domain.model.TvVideos

@Entity(
    tableName = "tv_video_entity",
)
@TypeConverters(VideoEntityConverter::class)
data class TvVideoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String? = "",
    @ColumnInfo(name = "videos_count")
    val videosCount: Int? = null,
    @ColumnInfo(name = "videos")
    val videos: List<VideoEntity>? = emptyList(),
    @ColumnInfo(name = "description")
    val description: String? = ""
)

fun TvVideoEntity.toTvVideos(): TvVideos {
    return TvVideos(
        videosCount = videosCount,
        videoDto = videos?.map { it.toVideo() },
        name = name,
        description = description,
        id = id
    )
}