package ir.box_net.test.data.model.dto.detail

import com.google.gson.annotations.SerializedName
import ir.box_net.test.domain.model.detail.VideoDetail

data class VideoDetailDto(
    @SerializedName("channel")
    val channelDto: ChannelDto?,
    @SerializedName("channel_id")
    val channelId: Int?,
    @SerializedName("content_type")
    val contentType: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("file_src")
    val fileSrc: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("publish_date")
    val publishDate: String?,
    @SerializedName("quality")
    val quality: Int?,
    @SerializedName("related_playlist_id")
    val relatedPlaylistId: Int?,
    @SerializedName("related_playlist_video_count")
    val relatedPlaylistVideoCount: Int?,
    @SerializedName("related_playlist_weight")
    val relatedPlaylistWeight: Int?,
    @SerializedName("thumbnail")
    val thumbnail: String?
)

fun VideoDetailDto.toVideoDetail(): VideoDetail {
    return VideoDetail(
        id = id,
        thumbnail = thumbnail,
        name = name,
        description = description,
        quality = quality,
        publishDate = publishDate,
        fileSrc = fileSrc,
        duration = duration,
        contentType = contentType,
        channelDto = channelDto?.toChannel(),
        channelId = id,
        relatedPlaylistId = relatedPlaylistId,
        relatedPlaylistVideoCount = relatedPlaylistVideoCount,
        relatedPlaylistWeight = relatedPlaylistWeight
    )
}