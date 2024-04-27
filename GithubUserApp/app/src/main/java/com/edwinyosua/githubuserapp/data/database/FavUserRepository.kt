package com.edwinyosua.githubuserapp.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavUserRepository(application: Application) {
    private val favUserDao: FavUserDao
    private val execService: ExecutorService = Executors.newSingleThreadExecutor()


    init {
        val db = FavUserDb.getDatabase(application)
        favUserDao = db.favUserDao()
    }

    fun getFavUserList(): LiveData<List<FavoriteUser>> = favUserDao.getFavUserList()

    fun getFavUserByUsername(userName: String): LiveData<FavoriteUser> = favUserDao.getFavoriteUserByUsername(userName)

    fun insert(favoriteUser: FavoriteUser) {
        execService.execute { favUserDao.insert(favoriteUser) }
    }

    fun delete(favoriteUser: FavoriteUser) {
        execService.execute {
            favUserDao.delete(favoriteUser)
        }
    }

    fun update(favoriteUser: FavoriteUser) {
        execService.execute { favUserDao.update(favoriteUser) }
    }
}