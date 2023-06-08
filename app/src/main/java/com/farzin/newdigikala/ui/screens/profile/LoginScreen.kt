package com.farzin.newdigikala.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.newdigikala.R
import com.farzin.newdigikala.ui.theme.*
import com.farzin.newdigikala.util.InputValidation.isValidEmail
import com.farzin.newdigikala.util.InputValidation.isValidPhoneNumber
import com.farzin.newdigikala.viewmodel.ProfileViewModel

@Composable
fun LoginScreen(
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.digi_settings
                        ), contentDescription = "",
                        modifier = Modifier
                            .padding(
                                MaterialTheme.spacing.small
                            )
                            .size(MaterialTheme.spacing.semiLarge),
                        tint = MaterialTheme.colors.selectedBottomBar
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Close",
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.small),
                        tint = MaterialTheme.colors.selectedBottomBar
                    )
                }
            }
        }
        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
        item {
            Image(
                modifier = Modifier
                    .size(200.dp),
                painter = painterResource(id = R.drawable.digi_smile),
                contentDescription = "",
            )
        }
        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
        item {
            Text(
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.semiLarge
                ),
                style = MaterialTheme.typography.h6,
                text = stringResource(id = R.string.loginTxt),
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            MyEditText(
                value = profileViewModel.inputPhoneState,
                placeholder = stringResource(id = R.string.phone_and_email),
                onValueChange = {
                    profileViewModel.inputPhoneState = it
                }
            )
        }
        item {
            MyButton(text = stringResource(id = R.string.digikala_entry)) {
                if (isValidEmail(profileViewModel.inputPhoneState)
                    || isValidPhoneNumber(profileViewModel.inputPhoneState)
                ) {
                    profileViewModel.screenState = ProfileScreenState.REGISTER_STATE
                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.login_error),
                        Toast.LENGTH_LONG
                    ).show()
                }


            }
        }
        item {
            Divider(
                color = MaterialTheme.colors.searchBarBg,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(top = MaterialTheme.spacing.medium)
            )
        }
        item {
            TermsAndRulesText(
                fullText = stringResource(id = R.string.terms_and_rules_full),
                underlinedText = listOf(
                    stringResource(id = R.string.terms_and_rules),
                    stringResource(id = R.string.privacy_and_rules)
                ),
                textColor = MaterialTheme.colors.semiDarkText,
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}