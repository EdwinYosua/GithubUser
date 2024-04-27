package com.edwinyosua.githubuserapp.ui.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edwinyosua.githubuserapp.ui.viewmodel.FavoriteUserViewModel

class FavViewModelFactory private constructor(private val mApp: Application) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: FavViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): FavViewModelFactory {
            if (INSTANCE == null) {
                synchronized(FavViewModelFactory::class.java) {
                    INSTANCE = FavViewModelFactory(application)
                }
            }
            return INSTANCE as FavViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteUserViewModel::class.java)) {
            return FavoriteUserViewModel(mApp) as T
        }
//        else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
//            return DetailViewModel(mApp) as T
//        }
        throw IllegalArgumentException("Unknown Viewmodel Class : ${modelClass.name}")
    }
}