package com.hrconnect.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hrconnect.android.presentation.navigation.NavigationRoot
import com.hrconnect.uikit.common.theme.HrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HrTheme {
                NavigationRoot()
            }
        }
    }
}