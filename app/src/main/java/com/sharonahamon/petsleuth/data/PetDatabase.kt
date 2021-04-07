package com.sharonahamon.petsleuth.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Pet::class, PetSummary::class, PetDetail::class, PetLastSeenLocation::class, ContactPerson::class],
    version = 1, // if you make any changes you must increment this version or the app will stop working
    exportSchema = false // if true, exports the schema to a folder, can be use for version history
)
abstract class PetDatabase : RoomDatabase() {

    abstract val petDao: PetDao
    abstract val petSummaryDao: PetSummaryDao
    abstract val petDetailDao: PetDetailDao
    abstract val petLastSeenLocationDao: PetLastSeenLocationDao
    abstract val contactPersonDao: ContactPersonDao

    // companion objects allow clients to invoke functions on this class
    // without having to instantiate the class
    companion object {
        @Volatile // up to date, no caching, no dirty data
        private var INSTANCE: PetDatabase? = null

        fun getInstance(context: Context): PetDatabase? {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PetDatabase::class.java,
                        "pet_database"
                    ).fallbackToDestructiveMigration()
                        .build()  // don't use a migration strategy, just drop/recreate

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}