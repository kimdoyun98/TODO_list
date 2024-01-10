package com.example.todo_list.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.todo_list.R

class SettingFragment : PreferenceFragmentCompat(){
    lateinit var prefs : SharedPreferences

    var pushAlertPreference: Preference? = null
    var alertTimePreference: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)

        if (rootKey == null) {
            // Preference 객체들 초기화
            pushAlertPreference = findPreference("pushAlert")
            alertTimePreference = findPreference("Alert_time")

            // SharedPreferences 객체 초기화
            prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())

            if (prefs.getString("Alert_time", "") != "") {
                // pushAlertPreference의 summary를 pushAlert key에 해당하는 값으로 설정
                alertTimePreference?.summary = prefs.getString("Alert_time", "")
            }
        }
    }

    private val prefListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when(key){
            "Alert_time" -> {
                val summary = prefs.getString("Alert_time", "")
                alertTimePreference?.summary = summary
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