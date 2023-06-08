package com.farzin.newdigikala.ui.screens.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farzin.newdigikala.ui.theme.digikalaRed
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun MyButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(
                start = MaterialTheme.spacing.semiLarge,
                end = MaterialTheme.spacing.semiLarge,
                bottom = MaterialTheme.spacing.medium
            ),
        shape = MaterialTheme.roundedShape.small
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.h5
        )


    }
}