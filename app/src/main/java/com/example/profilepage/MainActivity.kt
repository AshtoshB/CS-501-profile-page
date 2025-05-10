package com.example.profilepage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profilepage.ui.theme.ProfilePageTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilePageTheme {
                UserProfile()
            }
        }
    }
}

@Composable
fun UserProfile() {

    val pfp = R.drawable.profile_pic
    val user = "Ash Bhandari"
    val bio = "About Me:" +
            "\nAndroid Studio Mastermind | Compose Enjoyer | Gym Grinder"


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp)
        ) {

            Image(
                painter = painterResource(id = pfp),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(220.dp)

            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = user,
                fontSize = 30.sp

            )

            Spacer(modifier = Modifier.height(8.dp))

            // Bio
            Text(
                text = bio,
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Following $user")
                    }
                }
            ) {
                Text(text = "Follow")
            }
        }

        // Snackbar Host
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProfilePageTheme {
        UserProfile()
    }
}