package ir.box_net.test.data.model.dto

import com.google.gson.annotations.SerializedName
import ir.box_net.test.data.model.entity.VideoEntity
import ir.box_net.test.domain.model.Videos

data class VideoDto(
    @SerializedName("content_type")
    val contentType: Int? = null,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("file_size")
    val fileSize: Int? = null,
    @SerializedName("file_src")
    val fileSrc: String? = null,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("publish_date")
    val publishDate: String? = "",
    @SerializedName("quality")
    val quality: Int? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = ""
)

fun VideoDto.toVideoEntity(): VideoEntity {
    return VideoEntity(
        description = description,
        thumbnail = thumbnail,
        id = id,
        name = name,
        quality = quality,
        publishDate = publishDate,
        fileSrc = fileSrc,
        fileSize = fileSize,
        duration = duration,
        contentType = contentType
    )
}

fun VideoDto.toVideos(): Videos {
    return Videos(
        description = description,
        thumbnail = thumbnail,
        id = id,
        name = name,
        quality = quality,
        publishDate = publishDate,
        fileSrc = fileSrc,
        fileSize = fileSize,
        duration = duration,
        contentType = contentType
    )
}