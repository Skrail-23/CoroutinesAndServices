package com.example.jetpackcompose.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.ComponentActivity
import com.example.jetpackcompose.service.PopupService

class PopupServiceManager(private val context: Context) {

    // Request permission for notifications
    fun requestPermission() {
        val requestPermissionLauncher =
            (context as ComponentActivity).registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) startPopupService()
                else Toast.makeText(context, "Permission denied, notifications won't work", Toast.LENGTH_LONG).show()
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    // Start the PopupService
    fun startPopupService() {
        val serviceIntent = Intent(context, PopupService::class.java)
        context.startForegroundService(serviceIntent)
    }
}
