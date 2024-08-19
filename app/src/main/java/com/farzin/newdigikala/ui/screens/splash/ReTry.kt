package com.farzin.newdigikala.ui.screens.splash

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.R

@Composable
fun ReTry(onRetryClicked: () -> Unit) {


    Column(
        modifier = Modifier
            .clickable { onRetryClicked() }
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.check_net),
            style = MaterialTheme.typography.h6,
            color = Color.White
        )

        Icon(
            imageVector = Icons.Filled.Refresh,
            contentDescription ="",
            tint = Color.White,
            modifier = Modifier
                .padding(top = 8.dp)
        )

    }

}