package com.example.fullstackv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fullstackv2.features.NavGraphs
import com.example.fullstackv2.ui.theme.FullstackV2Theme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FullstackV2Theme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
