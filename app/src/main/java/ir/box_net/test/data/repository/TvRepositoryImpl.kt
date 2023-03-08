package ir.box_net.test.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import ir.box_net.test.common.Resources
import ir.box_net.test.data.local.dao.TvVideosDao
import ir.box_net.test.data.model.dto.detail.toVideoDetail
import ir.box_net.test.data.model.dto.toTvVideos
import ir.box_net.test.data.model.dto.toTvVideosEntity
import ir.box_net.test.data.model.entity.toTvVideos
import ir.box_net.test.data.service.TvApiService
import ir.box_net.test.domain.model.TvVideos
import ir.box_net.test.domain.model.detail.VideoDetail
import ir.box_net.test.domain.repository.TvRepository
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    private val apiService: TvApiService,
    private val tvVideosDao: TvVideosDao
) : TvRepository {
    override fun getAllVideos(): Flow<Resources<TvVideos>> = flow {

        if (tvVideosDao.getCount() > 0) {
            emit(Resources.Loading())
            val getVideos = tvVideosDao.getAllVideos().toTvVideos()
            emit(Resources.Loading())
            try {
                emit(Resources.Success(getVideos))
            } catch (e: Exception) {
                emit(Resources.Error(getVideos, message = e.message ?: "Err"))
            } catch (e: HttpException) {
                emit(Resources.Error(getVideos, message = e.message()))
            } catch (e: IOException) {
                emit(Resources.Error(getVideos, message = e.message ?: "Error"))
            }
        } else {
            emit(Resources.Loading())
            try {
                val getVideosDto = apiService.getAllVideos()
                tvVideosDao.insert(getVideosDto.toTvVideosEntity())
                emit(Resources.Success(getVideosDto.toTvVideos()))
            } catch (e: IOException) {
                emit(Resources.Error(message = e.message ?: "Error"))
            }
        }

    }

    override fun getVideoDetail(id: Int): Flow<Resources<VideoDetail>> = flow {
        emit(Resources.Loading())
        try {
            emit(Resources.Success(apiService.getVideoDetailById(id).toVideoDetail()))
        } catch (e: Exception) {
            emit(Resources.Error(message = e.message ?: "Error"))
        }
    }
}