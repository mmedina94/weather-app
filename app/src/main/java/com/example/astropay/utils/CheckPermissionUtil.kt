package com.example.astropay.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

class CheckPermissionUtil constructor(private val context: Context) {

    fun checkPermission(
        permission: String,
        requestPermission: () -> Unit,
        permissionGranted: () -> Unit
    ) {
        val permissionRequired =
            Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                    ContextCompat.checkSelfPermission(
                        context,
                        permission
                    ) == PackageManager.PERMISSION_GRANTED

        permissionRequired.check(
            assertFalse = { requestPermission.invoke() },
            assertTrue = { permissionGranted.invoke() }
        )
    }
}