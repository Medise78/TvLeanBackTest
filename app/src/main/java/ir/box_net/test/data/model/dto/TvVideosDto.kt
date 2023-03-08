package ir.box_net.test.data.model.dto

import com.google.gson.annotations.SerializedName
import ir.box_net.test.data.model.entity.TvVideoEntity
import ir.box_net.test.domain.model.TvVideos

data class TvVideosDto(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("videos")
    val videoDto: List<VideoDto>? = emptyList(),
    @SerializedName("videos_count")
    val videosCount: Int? = null
)

fun TvVideosDto.toTvVideosEntity(): TvVideoEntity {
    return TvVideoEntity(
        name = name,
        description = description,
        id = id,
        videosCount = videosCount,
        videos = videoDto?.map { it.toVideoEntity() }
    )
}

fun TvVideosDto.toTvVideos(): TvVideos {
    return TvVideos(
        name = name,
        description = description,
        id = id,
        videosCount = videosCount,
        videoDto = videoDto?.map { it.toVideos() }
    )
}