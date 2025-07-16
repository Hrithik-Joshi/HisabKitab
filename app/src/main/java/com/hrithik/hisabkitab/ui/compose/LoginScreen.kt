package com.hrithik.hisabkitab.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrithik.hisabkitab.R
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme
import com.hrithik.hisabkitab.ui.theme.interstate_blue_600
import com.hrithik.hisabkitab.ui.theme.interstate_blue_700
import com.hrithik.hisabkitab.ui.theme.interstate_white

@Composable
fun LoginScreen() {
    HisabKitabTheme {
        Scaffold(
            content =
                { padding ->
                    LoginContent(padding)
                },
            bottomBar = {
                CreateNewAccountSection()
            }
        )
    }
}

@Composable
fun LoginContent(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_wallet),
            contentDescription = "Create Account Icon",
            modifier = Modifier
                .padding(100.dp)
                .size(70.dp)
                .align(Alignment.TopCenter),
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(interstate_blue_700)
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    shape = RoundedCornerShape(16.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    shape = RoundedCornerShape(16.dp)
                )

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = interstate_blue_700),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(45.dp)
                ) {
                    Text(
                        text = "Log in",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        color = interstate_white
                    )

                }
            }
        }
    }
}

@Composable
fun CreateNewAccountSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(bottom = 40.dp, end = 12.dp, start = 12.dp).border(
                BorderStroke(1.dp, interstate_blue_600),shape = RoundedCornerShape(42.dp)).
            clickable {},
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Create New Account",
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
            color = interstate_blue_700
        )
    }
}


@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}