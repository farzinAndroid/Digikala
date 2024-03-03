package com.farzin.newdigikala.ui.screens.settings

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.HelpCenter
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.OtherHouses
import androidx.compose.material.icons.outlined.PestControl
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.newdigikala.MainActivity
import com.farzin.newdigikala.R
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.screens.profile.MenuRowItem
import com.farzin.newdigikala.ui.screens.profile.ProfileScreenState
import com.farzin.newdigikala.ui.theme.digikalaRed
import com.farzin.newdigikala.ui.theme.selectedBottomBar
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.Constants
import com.farzin.newdigikala.viewmodel.BasketViewModel
import com.farzin.newdigikala.viewmodel.DataStoreViewModel
import com.farzin.newdigikala.viewmodel.ProfileViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        SettingHeader(navController = navController)
        SettingsMenuSection(navController)


    }
}

@Composable
fun SettingHeader(navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.large, end = MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(R.string.setting),
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold
        )


        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "",
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                tint = MaterialTheme.colors.selectedBottomBar
            )
        }


    }

}

@Composable
fun SettingsMenuSection(
    navController: NavController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel(),
) {

    MenuRowItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.HelpCenter,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.repetitive_questions),
        isHaveDivider = true
    ) {
        navController.navigate(
            Screen.WebView.route + "?url=${Constants.DIGI_FAQ}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.PrivacyTip,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.privacy),
        isHaveDivider = true
    ) {
        navController.navigate(
            Screen.WebView.route + "?url=${Constants.DIGI_PRIVACY}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.OtherHouses,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.terms_of_use),
        isHaveDivider = true
    ) {
        navController.navigate(
            Screen.WebView.route + "?url=${Constants.DIGI_TERMS}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Call,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.contact_us),
        isHaveDivider = true
    ) {
        navController.navigate(
            Screen.WebView.route + "?url=${Constants.DIGI_TURLEARN}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.PestControl,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.error_report),
        isHaveDivider = true
    ) {
        navController.navigate(
            Screen.WebView.route + "?url=${Constants.DIGI_BUG}"
        )
    }

    MenuRowItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.StarRate,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.rating_to_digikal),
        isHaveDivider = true
    ) {
        navController.navigate(
            Screen.WebView.route + "?url=${Constants.DIGI_SCORE}"
        )
    }


    MenuRowItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Language,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.changing_lang),
        isHaveDivider = true,
        addComposable = {
            ChangeLanguage()
        }
    )


    MenuRowItem(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Logout,
                contentDescription = "",
                tint = MaterialTheme.colors.digikalaRed
            )
        },
        text = stringResource(R.string.sign_out),
        isHaveDivider = false,
        addComposable = {},
        color = MaterialTheme.colors.digikalaRed,
        onClick = {
            logOut(dataStoreViewModel, navController, profileViewModel,basketViewModel)
        }
    )

}

fun logOut(
    dataStoreViewModel: DataStoreViewModel,
    navController: NavController,
    profileViewModel: ProfileViewModel,
    basketViewModel: BasketViewModel
){
    basketViewModel.deleteAllCartItems()
    dataStoreViewModel.apply {
        saveUserToken("null")
        saveUserId("null")
        saveUserPassword("null")
        saveUserPhoneNumber("null")
        saveUserName("null")
    }
    profileViewModel.screenState = ProfileScreenState.LOGIN_STATE
    navController.navigate(Screen.Profile.route)
}

@Composable
fun ChangeLanguage(dataStoreViewModel: DataStoreViewModel = hiltViewModel()) {
    val activity = LocalContext.current as Activity

    val language = dataStoreViewModel.getUserLanguage()
    val checkState by remember { mutableStateOf(language) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.english))
        Switch(
            checked = checkState == Constants.PERSIAN_LANG,
            onCheckedChange = {
                dataStoreViewModel.saveUserLanguage(
                    if (language == Constants.ENGLISH_LANG)
                        Constants.PERSIAN_LANG
                    else
                        Constants.ENGLISH_LANG
                )
                activity.apply {
                    finish()
                    startActivity(Intent(activity, MainActivity::class.java))
                }
            }
        )
        Text(text = stringResource(R.string.persian))
    }

}












