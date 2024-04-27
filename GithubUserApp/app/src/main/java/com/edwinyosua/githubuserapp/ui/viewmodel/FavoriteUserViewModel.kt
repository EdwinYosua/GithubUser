package com.edwinyosua.githubuserapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edwinyosua.githubuserapp.data.database.FavUserRepository
import com.edwinyosua.githubuserapp.data.database.FavoriteUser

class FavoriteUserViewModel(application: Application) : ViewModel() {

    private val favUserRepo: FavUserRepository = FavUserRepository(application)

    fun getFavUserList(): LiveData<List<FavoriteUser>> = favUserRepo.getFavUserList()

    fun getFavUserByUsername(userName: String): LiveData<FavoriteUser> {
        return favUserRepo.getFavUserByUsername(userName)
    }

    fun insert(favUser: FavoriteUser) {
        favUserRepo.insert(favUser)
    }

    fun delete(favUser: FavoriteUser) {
        favUserRepo.delete(favUser)
    }




}