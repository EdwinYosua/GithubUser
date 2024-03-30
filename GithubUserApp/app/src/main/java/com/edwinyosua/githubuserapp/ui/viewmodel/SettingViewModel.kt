package com.edwinyosua.githubuserapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.edwinyosua.githubuserapp.data.datastore.SettingPreferences
import kotlinx.coroutines.launch

class SettingViewModel(private val pref: SettingPreferences) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkMode: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkMode)
        }
    }
}