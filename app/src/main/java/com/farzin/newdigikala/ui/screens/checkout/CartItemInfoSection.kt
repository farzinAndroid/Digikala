package com.farzin.newdigikala.ui.screens.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.R
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun CartItemInfoSection() {

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.medium
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Icon(
            painter = painterResource(R.drawable.info),
            contentDescription ="",
            modifier = Modifier
                .weight(0.1f)
                .size(26.dp)
        )

        Text(
            text = stringResource(R.string.factor_info),
            modifier = Modifier
                .weight(0.9f),
            textAlign = TextAlign.Start,
            style = Typography.h6,
            color = Color.DarkGray
        )

    }

}