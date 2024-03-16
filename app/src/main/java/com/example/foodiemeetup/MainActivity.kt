package com.example.foodiemeetup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.foodiemeetup.ViewModels.LoginViewModel
import com.example.foodiemeetup.app.FoodieMeetupApp
import com.example.foodiemeetup.screens.MainPage
import com.example.foodiemeetup.ui.theme.FoodieMeetUpTheme

class MainActivity : ComponentActivity() {
    private lateinit var myViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myViewModel = LoginViewModel()
        setContent {
            FoodieMeetupApp(viewModel=myViewModel)

        }
    }
}

