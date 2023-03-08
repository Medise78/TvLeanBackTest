package ir.box_net.test.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.box_net.test.data.model.entity.TvVideoEntity


@Dao
interface TvVideosDao {

    @Query("SELECT * FROM tv_video_entity")
    suspend fun getAllVideos(): TvVideoEntity

    @Query("SELECT COUNT(id) FROM tv_video_entity")
    suspend fun getCount():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvVideoEntity: TvVideoEntity)
}