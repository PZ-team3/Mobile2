package com.example.foodiemeetup.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foodiemeetup.R
import com.example.foodiemeetup.ViewModels.PasswordChangeScreenViewModel
import com.example.foodiemeetup.ViewModels.PreferencesManager
import com.example.foodiemeetup.components.ButtonComponent
import com.example.foodiemeetup.components.HeadingTextComponent
import com.example.foodiemeetup.components.PasswordTextFieldComponent
import com.example.foodiemeetup.components.TextToLeftComponent
import com.example.foodiemeetup.ui.theme.BgColor

@Composable
fun ChangePasswordScreen(viewModel: PasswordChangeScreenViewModel, navController: NavHostController) {

    val context = LocalContext.current
    val appPreferences = remember { PreferencesManager.create(context) }
    val token = appPreferences.getString("token","")

    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    val keyy = remember { mutableStateOf("x")}

    key(keyy.value) {
        keyy.value = "x"
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BgColor)
                .padding(top = 28.dp, start = 28.dp, end = 28.dp)

        ) {
            HeadingTextComponent(value = "Change Password")
            Spacer(modifier = Modifier.height(28.dp))
            /*TextToLeftComponent(20, "Old password")
        PasswordTextFieldComponent(
            labelValue = stringResource(id = R.string.password),
            painterResource(id = R.drawable.ic_lock),
            helperValue = oldPassword,
            onhelperValueChange = { oldPassword = it })
        Spacer(modifier = Modifier.height(20.dp))

         */
            TextToLeftComponent(20, "New password")
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock),
                helperValue = newPassword,
                onhelperValueChange = { newPassword = it })
            Spacer(modifier = Modifier.height(20.dp))
            TextToLeftComponent(20, "Repeat new password")
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock),
                helperValue = repeatPassword,
                onhelperValueChange = { repeatPassword = it })
            Spacer(modifier = Modifier.height(24.dp))

            ButtonComponent(value = "Change", onButtonClicked = {
                if (newPassword == repeatPassword) {
                    viewModel.postPasswordChange(token, newPassword, context)
                    newPassword = ""
                    repeatPassword = ""
                    keyy.value = "y"
                } else {
                    Toast.makeText(
                        context,
                        "New password and Repeated password are not the same",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }, isEnabled = true)

        }
    }


}

@Composable
@Preview
fun ChangePasswordScreenPreview() {
    ChangePasswordScreen(PasswordChangeScreenViewModel(), navController = rememberNavController())
}