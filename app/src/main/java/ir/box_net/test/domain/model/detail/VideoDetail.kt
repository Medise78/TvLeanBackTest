package ir.box_net.test.domain.model.detail

data class VideoDetail(
    val channelDto: Channel?,
    val channelId: Int?,
    val contentType: Int?,
    val description: String?,
    val duration: Int?,
    val fileSrc: String?,
    val id: Int?,
    val name: String?,
    val publishDate: String?,
    val quality: Int?,
    val relatedPlaylistId: Int?,
    val relatedPlaylistVideoCount: Int?,
    val relatedPlaylistWeight: Int?,
    val thumbnail: String?
)