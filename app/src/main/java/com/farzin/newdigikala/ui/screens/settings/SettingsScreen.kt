package com.farzin.newdigikala.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.HelpCenter
import androidx.compose.material.icons.outlined.OtherHouses
import androidx.compose.material.icons.outlined.PestControl
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.screens.profile.MenuRowItem
import com.farzin.newdigikala.ui.theme.selectedBottomBar
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.Constants

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
fun SettingsMenuSection(navController: NavController) {

    MenuRowItem(
        icon = {
            Image(
                imageVector = Icons.Outlined.HelpCenter,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.repetitive_questions),
        isHaveDivider =true
    ){
        navController.navigate(
            Screen.WebView.route+"?url=${Constants.DIGI_FAQ}"
        )
    }

    MenuRowItem(
        icon = {
            Image(
                imageVector = Icons.Outlined.PrivacyTip,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.privacy),
        isHaveDivider =true
    ){
        navController.navigate(
            Screen.WebView.route+"?url=${Constants.DIGI_PRIVACY}"
        )
    }

    MenuRowItem(
        icon = {
            Image(
                imageVector = Icons.Outlined.OtherHouses,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.terms_of_use),
        isHaveDivider =true
    ){
        navController.navigate(
            Screen.WebView.route+"?url=${Constants.DIGI_TERMS}"
        )
    }

    MenuRowItem(
        icon = {
            Image(
                imageVector = Icons.Outlined.Call,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.contact_us),
        isHaveDivider =true
    ){
        navController.navigate(
            Screen.WebView.route+"?url=${Constants.DIGI_TURLEARN}"
        )
    }

    MenuRowItem(
        icon = {
            Image(
                imageVector = Icons.Outlined.PestControl,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.error_report),
        isHaveDivider =true
    ){
        navController.navigate(
            Screen.WebView.route+"?url=${Constants.DIGI_BUG}"
        )
    }

    MenuRowItem(
        icon = {
            Image(
                imageVector = Icons.Outlined.StarRate,
                contentDescription = "",
            )
        },
        text = stringResource(R.string.rating_to_digikal),
        isHaveDivider =true
    ){
        navController.navigate(
            Screen.WebView.route+"?url=${Constants.DIGI_SCORE}"
        )
    }
}












