package com.example.todo_list.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.todo_list.Activity.MainActivity
import com.example.todo_list.R

class SettingFragment : PreferenceFragmentCompat(){
    val homeActivityToolbar: Toolbar
        get() = (requireActivity() as MainActivity).binding.toolbar

    lateinit var prefs : SharedPreferences

    var pushAlertPreference: Preference? = null
    var Alert_timePreference: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
        homeActivityToolbar.title = "Setting"

        if (rootKey == null) {
            // Preference 객체들 초기화
            pushAlertPreference = findPreference("pushAlert")
            Alert_timePreference = findPreference("Alert_time")

            // SharedPreferences 객체 초기화
            prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())

            if (prefs.getString("Alert_time", "") != "") {
                // pushAlertPreference의 summary를 pushAlert key에 해당하는 값으로 설정
                Alert_timePreference?.summary = prefs.getString("Alert_time", "")
            }
        }
    }

    val prefListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
        when(key){
            "Alert_time" -> {
                val summary = prefs.getString("Alert_time", "")
                Alert_timePreference?.summary = summary
            }
        }
    }

    override fun onResume() {
        super.onResume()
        prefs.registerOnSharedPreferenceChangeListener(prefListener)
    }

    override fun onPause() {
        super.onPause()
        prefs.unregisterOnSharedPreferenceChangeListener(prefListener)
    }
}