package ir.box_net.test.data.service

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import ir.box_net.test.data.model.dto.TvVideosDto
import ir.box_net.test.data.model.dto.detail.VideoDetailDto

interface TvApiService {

    @Headers(
        "SESSION-KEY: 43581c49f795564442a066b11e95bcdc7dba9ac6062178d9c2fb65acce4ba761"
    )
    @GET("playlist/1/?page=1&pageSize=10")
    suspend fun getAllVideos(): TvVideosDto

    @Headers(
        value = [
            "SESSION-KEY: 43581c49f795564442a066b11e95bcdc7dba9ac6062178d9c2fb65acce4ba761"
        ]
    )
    @GET("video/{id}/")
    suspend fun getVideoDetailById(
        @Path(value = "id") id: Int
    ): VideoDetailDto
}