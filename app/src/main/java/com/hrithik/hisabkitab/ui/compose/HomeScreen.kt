package com.hrithik.hisabkitab.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrithik.hisabkitab.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme
import com.hrithik.hisabkitab.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onSignOutClicked: () -> Unit
) {

    val showBottomSheet by homeViewModel.showBottomSheet.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val isSignedOut by homeViewModel.isSignedOut.collectAsState()

    if( isSignedOut) {
        onSignOutClicked()
    }

    HisabKitabTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    title = {},
                    actions = {
                        IconButton(
                            onClick = { homeViewModel.showBottomSheet(true) }
                        ) {
                            Image(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Settings Icon",
                                modifier = Modifier,
                                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                            )
                        }
                    }
                )
            },
            content = { padding ->
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    if (showBottomSheet) {
                        ModalBottomSheet(
                            onDismissRequest = { homeViewModel.showBottomSheet(false) },
                            sheetState = sheetState
                        ) {

                            Row(modifier = Modifier.fillMaxWidth()) {
                                Image(
                                    painter = painterResource(R.drawable.person_icon),
                                    contentDescription = "Settings Icon",
                                    modifier = Modifier
                                        .padding(20.dp)
                                        .size(20.dp),
                                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                                )
                                Text(
                                    text = "Account",
                                    modifier = Modifier
                                        .padding(top = 20.dp),
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                )
                                Column(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                                    Image(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Settings Icon",
                                        modifier = Modifier
                                            .padding(end = 12.dp)
                                            .align(Alignment.End)
                                            .clickable(onClick = { homeViewModel.showBottomSheet(false) }),
                                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                                    )
                                }
                            }
                           Row(modifier = Modifier.fillMaxWidth()) {
                               Image(
                                   painter = painterResource(R.drawable.signout_icon),
                                   contentDescription = "Sign out Icon",
                                   modifier = Modifier
                                       .padding(20.dp)
                                       .size(20.dp),
                                   colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                               )
                               Text(
                                   text = "Sign Out",
                                   modifier = Modifier
                                       .padding(top = 20.dp)
                                   .clickable(onClick = { homeViewModel.signOut()}),
                                   style = MaterialTheme.typography.labelLarge.copy(
                                       fontWeight = FontWeight.Bold,
                                       fontSize = 18.sp
                                   )
                               )
                           }
                        }
                    }
                }
            }
        )
    }
}