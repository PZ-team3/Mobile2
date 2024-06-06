package com.example.foodiemeetup.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodiemeetup.ViewModels.ChatPeopleViewModel
import com.example.foodiemeetup.ViewModels.PreferencesManager
import com.example.foodiemeetup.components.HeadingTextComponent
import com.example.foodiemeetup.components.MatchUsers
import com.example.foodiemeetup.models.AvailableChat
import com.example.foodiemeetup.ui.theme.BgColor


@Composable
fun ChatPeopleScreen(viewModel: ChatPeopleViewModel, navController: NavController) {
    val context = LocalContext.current
    val appPreferences = remember { PreferencesManager.create(context) }
    val token by remember { mutableStateOf(appPreferences.getString("token")) }
    var userMatches: List<AvailableChat> by remember { mutableStateOf(listOf()) }

    viewModel.getAvailableChats(token, context) { uM -> userMatches = uM }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor)
            .padding(top=28.dp, start=28.dp, end=28.dp)

    ){
        Spacer(modifier = Modifier.height(28.dp))
        HeadingTextComponent(value = "Chats")
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            item{Spacer(modifier = Modifier.height(20.dp))}
            items(userMatches) { chat ->
                MatchUsers(
                    username = chat.username,
                    onButtonClicked = {
                        Log.d("abc", "EventsScreen: " + chat.chat.chatId)
                        navController.navigate("chatDetail/${chat.chat.chatId}/${chat.username}")
                    },
                    isEnabled = true
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}