package com.example.todo_list.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.todo_list.R
import com.example.todo_list.common.MyApplication

class SettingFragment : PreferenceFragmentCompat(){
    lateinit var prefs : SharedPreferences

    //var pushAlertPreference: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)

        if (rootKey == null) {
            // Preference 객체들 초기화
            //pushAlertPreference = findPreference("pushAlert")

            // SharedPreferences 객체 초기화
            prefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        }
    }

    private val prefListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when(key){
            "pushAlert" -> {
                if(MyApplication.prefs.getAlarm("pushAlarm")) MyApplication.prefs.setAlarm("pushAlarm", false)
                else MyApplication.prefs.setAlarm("pushAlarm", true)
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