package com.farzin.newdigikala.ui.screens.checkout

import android.provider.MediaStore.Audio.Radio
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.R
import com.farzin.newdigikala.ui.theme.Orange
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun DeliveryBottomSheet() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                RadioButton(
                    selected = true,
                    onClick = { }
                )

                Text(
                    text = stringResource(R.string.pishtaz_post),
                    style = Typography.h6,
                    color = MaterialTheme.colors.darkText,
                )

            }


            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.digi_plus_icon),
                    contentDescription = "",
                    modifier = Modifier.size(18.dp)
                )

                Text(
                    text = stringResource(R.string.delivery_delay),
                    style = Typography.h6,
                    color = MaterialTheme.colors.Orange,
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                )
            }
        }

    }

}