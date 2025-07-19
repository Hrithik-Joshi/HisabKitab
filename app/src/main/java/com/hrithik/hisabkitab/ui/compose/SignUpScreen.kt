package com.hrithik.hisabkitab.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseUser
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme
import com.hrithik.hisabkitab.ui.theme.interstate_blue_700
import com.hrithik.hisabkitab.ui.theme.interstate_white
import com.hrithik.hisabkitab.util.Resource
import com.hrithik.hisabkitab.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onBackClick: () -> Unit,
    onAlreadyHaveAccountClick: () -> Unit,
    onSignUpSuccess: () -> Unit,
    authViewModel: AuthViewModel
) {
    val authResource = authViewModel.signupFlow.collectAsState()

    HisabKitabTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Create New Account",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick, modifier = Modifier.testTag("BackNav")) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = ""
                            )
                        }
                    },
                )
            },
            content = { padding ->
                SignUpContent(padding, onSignUpSuccess, authViewModel, authResource)
            },
            bottomBar = {
                AlreadyHadAccount(onAlreadyHaveAccountClick)
            }
        )
    }
}

@Composable
fun SignUpContent(
    padding: PaddingValues,
    onSignUpSuccess: () -> Unit,
    authViewModel: AuthViewModel,
    authResource: State<Resource<FirebaseUser>?>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var fullName by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("FullName") },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },

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
                onClick = { authViewModel.signupUser(fullName, email, password) },
                colors = ButtonDefaults.buttonColors(containerColor = interstate_blue_700),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(45.dp)
            ) {
                Text(
                    text = "Sign up",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    color = interstate_white
                )

            }
        }

        var showErrorDialog by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf("") }

        authResource.value?.let {
            when (it) {
                is Resource.Failure -> {
                    showErrorDialog = true
                    errorMessage = it.exception.message ?: "An error occurred while registering"
                }

                Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(alignment = Alignment.Center)

                    )
                }

                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        onSignUpSuccess()
                    }
                }
            }
        }

        if (showErrorDialog) {
            ErrorDialog(
                title = "Registration Error",
                message = errorMessage,
                onDismiss = { showErrorDialog = false }
            )
        }
    }
}

@Composable
fun AlreadyHadAccount(onAlreadyHaveAccountClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp)
            .clickable { onAlreadyHaveAccountClick() },
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = "I already have an account",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = interstate_blue_700,

            )
    }
}
