package ir.box_net.test.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.box_net.test.domain.model.Videos

@Entity(
    tableName = "video_entity"
)
data class VideoEntity(
    val contentType: Int? = null,
    @ColumnInfo(name = "description")
    val description: String? = "",
    @ColumnInfo(name = "duration")
    val duration: Int? = null,
    @ColumnInfo(name = "file_size")
    val fileSize: Int? = null,
    @ColumnInfo(name = "file_src")
    val fileSrc: String? = "",
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String? = "",
    @ColumnInfo(name = "publish_date")
    val publishDate: String? = "",
    @ColumnInfo(name = "quality")
    val quality: Int? = null,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String? = ""
)

fun VideoEntity.toVideo(): Videos {
    return Videos(
        id = id,
        description = description,
        name = name,
        contentType = contentType,
        duration = duration,
        fileSize = fileSize,
        fileSrc = fileSrc,
        publishDate = publishDate,
        quality = quality,
        thumbnail = thumbnail
    )
}