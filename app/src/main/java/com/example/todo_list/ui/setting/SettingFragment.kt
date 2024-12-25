package com.example.todo_list.ui.setting

import android.Manifest
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.todo_list.R
import com.example.todo_list.util.MyApplication
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

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

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val prefListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when(key){
            "pushAlert" -> {
                if(MyApplication.prefs.getAlarm("pushAlarm")) MyApplication.prefs.setAlarm("pushAlarm", false)
                else {
                    TedPermission.create()
                        .setPermissionListener(permission)
                        .setDeniedMessage("권한이 거부되었습니다. 설정 > 권한에서 허용해주세요.")
                        .setPermissions(Manifest.permission.POST_NOTIFICATIONS)
                        .check()
                    MyApplication.prefs.setAlarm("pushAlarm", true)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onResume() {
        super.onResume()
        prefs.registerOnSharedPreferenceChangeListener(prefListener)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onPause() {
        super.onPause()
        prefs.unregisterOnSharedPreferenceChangeListener(prefListener)
    }

    private val permission = object : PermissionListener {
        override fun onPermissionGranted() {}

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            Toast.makeText(MyApplication.instance.applicationContext, "권한 거부", Toast.LENGTH_SHORT).show()
        }
    }
}