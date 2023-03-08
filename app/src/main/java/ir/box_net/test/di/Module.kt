package ir.box_net.test.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ir.box_net.test.common.GsonParser
import ir.box_net.test.common.Util
import ir.box_net.test.data.local.TvDataBase
import ir.box_net.test.data.local.converter.VideoEntityConverter
import ir.box_net.test.data.repository.TvRepositoryImpl
import ir.box_net.test.data.service.TvApiService
import ir.box_net.test.domain.repository.TvRepository
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideTvApi(): TvApiService {
        return Retrofit.Builder()
            .baseUrl(Util.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalDataBase(
        application: Application
    ): TvDataBase {
        return Room
            .databaseBuilder(
                application,
                TvDataBase::class.java,
                TvDataBase.DB_NAME
            ).addTypeConverter(VideoEntityConverter(GsonParser(Gson())))
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTvRepository(apiService: TvApiService, dataBase: TvDataBase): TvRepository {
        return TvRepositoryImpl(apiService, dataBase.tvVideosDao())
    }


}