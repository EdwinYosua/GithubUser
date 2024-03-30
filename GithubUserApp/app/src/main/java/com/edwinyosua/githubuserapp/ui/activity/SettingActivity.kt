package com.edwinyosua.githubuserapp.ui.activity

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.edwinyosua.githubuserapp.databinding.ActivitySettingBinding
import com.edwinyosua.githubuserapp.ui.viewmodel.SettingViewModel
import com.edwinyosua.githubuserapp.utils.SettingPreferences
import com.edwinyosua.githubuserapp.utils.SettingViewModelFactory
import com.edwinyosua.githubuserapp.utils.dataStore

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private lateinit var settingViewModel: SettingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val pref = SettingPreferences.getInstance(application.dataStore)
        settingViewModel = ViewModelProvider(this, SettingViewModelFactory(pref)).get(SettingViewModel::class.java)


        settingViewModel.getThemeSettings().observe(this) { isDarkMode: Boolean ->
            if(isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }

        }

        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingViewModel.saveThemeSetting(isChecked)
        }

//
//        with(binding) {
//            switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
//                if (isChecked) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                    switchTheme.isChecked = true
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                    switchTheme.isChecked = false
//                }
//                settingViewModel.saveThemeSetting(isChecked)
//            }
//        }

    }
}