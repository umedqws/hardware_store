package com.example.hardwarestore.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [Users::class, Products::class,Categories::class,Basket::class,History::class,UserTime::class,Review::class],
    version = 1,
    exportSchema = false
)
abstract class DBase: RoomDatabase() {
    abstract fun usersDao():UserDao
    abstract fun productDao():ProductDao
    abstract fun categoryDao():CategoryDao
    abstract fun basketDao():BasketDao
    abstract fun historyDao():HistoryDao
    abstract fun timeDao():TimeDao
    abstract fun reviewDao():ReviewDao
    companion object{
        @Volatile
        private var INSTANCE:DBase? = null

        fun getInstance(context: Context):DBase{
            kotlin.synchronized(this) {

                if (INSTANCE == null) {
                    // мы еще ни разу не создавали БД
                    val builder = Room.databaseBuilder(
                        context.applicationContext,
                        DBase::class.java,
                        "HardwareStore_db"
                    )

                    INSTANCE = builder.build()
                    return INSTANCE!!
                }
                // БД уже была создана
                return INSTANCE!!
            }
        }
    }
}
