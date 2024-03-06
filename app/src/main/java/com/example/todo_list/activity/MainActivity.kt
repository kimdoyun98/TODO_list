package com.example.todo_list.activity

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todo_list.R
import com.example.todo_list.databinding.ActivityMainBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        TedPermission.create()
            .setPermissionListener(permission)
            .setDeniedMessage("권한이 거부되었습니다. 설정 > 권한에서 허용해주세요.")
            .setPermissions(Manifest.permission.POST_NOTIFICATIONS)
            .check()

        binding.swiperefreshlayout.setOnRefreshListener {
            binding.swiperefreshlayout.isRefreshing = false
        }


        val navController = findNavController(R.id.main_fragment)

        binding.navBar.setupWithNavController(navController)

    }
    private val permission = object : PermissionListener {
        override fun onPermissionGranted() {
            Toast.makeText(this@MainActivity, "권한 허가", Toast.LENGTH_SHORT).show()
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            Toast.makeText(this@MainActivity, "권한 거부", Toast.LENGTH_SHORT).show()
        }
    }
}