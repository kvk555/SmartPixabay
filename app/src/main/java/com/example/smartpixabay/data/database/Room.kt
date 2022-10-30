package com.example.smartpixabay.data.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Dao
interface ImageDao {
    @Query("select * FROM databaseimage")
    fun getImages(): List<DatabaseImage>

    @Query("select * FROM databaseimage WHERE id=:id ")
    fun getImageById(id: String): DatabaseImage

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<DatabaseImage>)
}

@Database(entities = [DatabaseImage::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {
    abstract val imageDao: ImageDao
}

private lateinit var INSTANCE: ImageDatabase

fun getDatabase(context: Context): ImageDatabase {
    synchronized(ImageDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ImageDatabase::class.java,
                "images"
            ).build()
        }
    }
    return INSTANCE
}
