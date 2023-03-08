package ir.box_net.test.domain.repository

import kotlinx.coroutines.flow.Flow
import ir.box_net.test.common.Resources
import ir.box_net.test.domain.model.TvVideos
import ir.box_net.test.domain.model.detail.VideoDetail

interface TvRepository {

    fun getAllVideos(): Flow<Resources<TvVideos>>
    fun getVideoDetail(id: Int): Flow<Resources<VideoDetail>>
}