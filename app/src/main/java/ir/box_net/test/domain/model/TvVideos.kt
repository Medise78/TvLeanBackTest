package ir.box_net.test.domain.model

data class TvVideos(
    val description: String?,
    val id: Int,
    val name: String?,
    val videoDto: List<Videos>?,
    val videosCount: Int?
)

