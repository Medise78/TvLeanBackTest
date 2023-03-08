package ir.box_net.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.box_net.test.data.local.converter.VideoEntityConverter
import ir.box_net.test.data.local.dao.TvVideosDao
import ir.box_net.test.data.model.entity.TvVideoEntity
import ir.box_net.test.data.model.entity.VideoEntity

@Database(
    entities = [TvVideoEntity::class, VideoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(VideoEntityConverter::class)
abstract class TvDataBase : RoomDatabase() {

    abstract fun tvVideosDao(): TvVideosDao

    companion object {
        const val DB_NAME = "tv_database"
    }
}