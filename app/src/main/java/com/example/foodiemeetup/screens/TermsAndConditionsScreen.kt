package com.example.foodiemeetup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiemeetup.R
import com.example.foodiemeetup.components.HeadingTextComponent
import com.example.foodiemeetup.navigation.FoodieMeetUpRouter
import com.example.foodiemeetup.navigation.Screen
import com.example.foodiemeetup.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen() {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)) {

        Column {
            HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions_header))
            TermsAndConditionsText()
        }
    }
    SystemBackButtonHandler {
        FoodieMeetUpRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Composable
fun TermsAndConditionsText() {
    val termsText = """
        Welcome to FoodieMeetUp!

        By registering for and using our application, you agree to the following terms and conditions:

        1. **User Information**: During registration, we collect personal information including your name, age, and email address. This information will be accessible to the application owners and used to enhance your experience with the app.

        2. **Age Restriction**: This application is intended for users who are 18 years of age or older. By using this app, you confirm that you meet this age requirement.

        3. **User Conduct**: Users are expected to behave respectfully towards other users and not to engage in any activity that could harm or harass others.

        4. **Data Usage**: The data collected from users will be used to personalize the app experience, provide customer support, and for analytical purposes to improve the app. We will not share your personal information with third parties without your consent.

        5. **Account Security**: You are responsible for maintaining the confidentiality of your account information and for all activities that occur under your account.

        6. **Modifications to Terms**: We reserve the right to modify these terms and conditions at any time. We will notify users of any significant changes through the app or via email.

        By continuing to use the FoodieMeetUp app, you acknowledge that you have read, understood, and agree to these terms and conditions.
    """.trimIndent()

    Text(
        text = termsText,
        fontSize = 14.sp,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Preview
@Composable
fun TermsAndConditionsScreenPreview(){
    TermsAndConditionsScreen()
}