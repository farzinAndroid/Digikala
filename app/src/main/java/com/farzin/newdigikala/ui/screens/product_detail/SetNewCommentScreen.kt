package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.farzin.newdigikala.R
import com.farzin.newdigikala.ui.theme.DarkCyan
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.amber
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.grayAlpha
import com.farzin.newdigikala.ui.theme.grayCategory
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.semiDarkText
import com.farzin.newdigikala.ui.theme.spacing

@Composable
fun SetNewCommentScreen(
    navController: NavController,
    productName: String,
    productId: String,
    imageUrl: String,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        Header(
            navController = navController,
            productName = productName,
            imageUrl = imageUrl
        )

        SeekBarSection()
        CommentForm()


    }

}


@Composable
private fun Header(
    navController: NavController,
    productName: String,
    imageUrl: String,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.extraSmall,
                horizontal = MaterialTheme.spacing.small
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = ""
            )
        }


        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
                .clip(MaterialTheme.roundedShape.small)
                .size(50.dp)
        )


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(R.string.your_comment),
                style = Typography.h3,
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold,
            )


            Text(
                text = productName,
                style = Typography.h6,
                color = MaterialTheme.colors.semiDarkText,
                fontWeight = FontWeight.Normal,
            )


        }


    }

}


@Composable
private fun SeekBarSection() {

    var sliderValue by remember {
        mutableStateOf(0f)
    }

    val scoreText = when (sliderValue.toInt()) {
        1 -> ""
        2 -> stringResource(R.string.very_bad)
        3 -> stringResource(R.string.bad)
        4 -> stringResource(R.string.normal)
        5 -> stringResource(R.string.good)
        6 -> stringResource(R.string.very_good)
        else -> ""
    }



    Text(
        text = scoreText,
        style = Typography.h2,
        color = MaterialTheme.colors.darkText,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.biggerMedium),
        textAlign = TextAlign.Center
    )


    Slider(
        value = sliderValue,
        onValueChange = {
            sliderValue = it
        },
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.large),
        onValueChangeFinished = {},
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.amber,
            activeTrackColor = MaterialTheme.colors.amber,
            inactiveTrackColor = MaterialTheme.colors.grayCategory,
            activeTickColor = MaterialTheme.colors.amber,
            inactiveTickColor = MaterialTheme.colors.grayAlpha,
        ),
        steps = 4,
        valueRange = 1f..6f
    )


    Divider(
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.semiMedium),
        color = MaterialTheme.colors.grayCategory,
        thickness = 1.dp,
    )

}


@Composable
private fun CommentForm(){

    var commentTitle by remember { mutableStateOf("") }
    var commentBody by remember { mutableStateOf("") }


   Column(
       modifier = Modifier
           .fillMaxWidth()
           .padding(horizontal = MaterialTheme.spacing.medium)
   ) {
       Text(
           text = stringResource(R.string.say_your_comment) ,
           style = Typography.h3,
           color = MaterialTheme.colors.darkText,
           fontWeight = FontWeight.Bold,
           modifier = Modifier
               .padding(vertical = MaterialTheme.spacing.medium)
           )

       Text(
           modifier = Modifier
               .padding(MaterialTheme.spacing.extraSmall),
           text = stringResource(id = R.string.comment_title),
           style = MaterialTheme.typography.h5,
           color = MaterialTheme.colors.darkText,
       )


       OutlinedTextField(
           modifier = Modifier
               .fillMaxWidth(),
           value = commentTitle,
           onValueChange = { commentTitle = it },
           maxLines = 1,
           singleLine = true,
           shape = MaterialTheme.roundedShape.small,
           colors = TextFieldDefaults.textFieldColors(
               textColor = MaterialTheme.colors.darkText,
               backgroundColor = MaterialTheme.colors.grayCategory,
               focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
               unfocusedIndicatorColor = Color.Transparent,
               disabledIndicatorColor = Color.Transparent
           )
       )



       Text(
           modifier = Modifier
               .padding(MaterialTheme.spacing.extraSmall),
           text = stringResource(id = R.string.comment_text),
           style = MaterialTheme.typography.h5,
           color = MaterialTheme.colors.darkText,
       )


       OutlinedTextField(
           modifier = Modifier
               .fillMaxWidth()
               .height(100.dp),
           value = commentBody,
           onValueChange = { commentBody = it },
           shape = MaterialTheme.roundedShape.small,
           colors = TextFieldDefaults.textFieldColors(
               textColor = MaterialTheme.colors.darkText,
               backgroundColor = MaterialTheme.colors.grayCategory,
               focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
               unfocusedIndicatorColor = Color.Transparent,
               disabledIndicatorColor = Color.Transparent
           ),
           maxLines = 3,
       )



   }

}
