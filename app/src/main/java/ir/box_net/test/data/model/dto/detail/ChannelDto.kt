package ir.box_net.test.data.model.dto.detail

import com.google.gson.annotations.SerializedName
import ir.box_net.test.domain.model.detail.Channel

data class ChannelDto(
    @SerializedName("background")
    val background: String?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("description_fa")
    val descriptionFa: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("splash")
    val splash: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
)

fun ChannelDto.toChannel(): Channel {
    return Channel(
        description = description,
        name = name,
        color = color,
        id = id,
        thumbnail = thumbnail,
        background = background,
        descriptionFa = descriptionFa,
        logo = logo,
        splash = splash,
    )
}