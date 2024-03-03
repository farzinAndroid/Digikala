package com.farzin.newdigikala.ui.screens.product_detail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.product_detail.NewComment
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.ui.components.OurLoading
import com.farzin.newdigikala.ui.theme.DarkCyan
import com.farzin.newdigikala.ui.theme.DigikalaDarkRed
import com.farzin.newdigikala.ui.theme.DigikalaLightGreen
import com.farzin.newdigikala.ui.theme.DigikalaLightRed
import com.farzin.newdigikala.ui.theme.Green
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.amber
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.grayAlpha
import com.farzin.newdigikala.ui.theme.grayCategory
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.semiDarkText
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.Constants
import com.farzin.newdigikala.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

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

        CommentForm(productId, navController)


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
private fun CommentForm(
    productId: String,
    navController: NavController,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {


    var sliderValue by remember {
        mutableStateOf(0f)
    }

    val context = LocalContext.current

    val scoreText = when (sliderValue.toInt()) {
        1 -> ""
        2 -> stringResource(R.string.very_bad)
        3 -> stringResource(R.string.bad)
        4 -> stringResource(R.string.normal)
        5 -> stringResource(R.string.good)
        6 -> stringResource(R.string.very_good)
        else -> ""
    }

    val scoreColor = when (sliderValue.toInt()) {
        1 -> MaterialTheme.colors.amber
        2 -> MaterialTheme.colors.DigikalaDarkRed
        3 -> MaterialTheme.colors.DigikalaLightRed
        4 -> MaterialTheme.colors.amber
        5 -> MaterialTheme.colors.DigikalaLightGreen
        6 -> MaterialTheme.colors.Green
        else -> MaterialTheme.colors.amber
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
            thumbColor = scoreColor,
            activeTrackColor = scoreColor,
            inactiveTrackColor = MaterialTheme.colors.grayCategory,
            activeTickColor = scoreColor,
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


    //////////////////////////////////////////////////////////////////////////////////////

    var commentTitle by remember { mutableStateOf("") }
    var commentBody by remember { mutableStateOf("") }


    var loading by remember {
        mutableStateOf(false)
    }

    val c = LocalContext.current

    LaunchedEffect(true) {

        viewModel.newCommentResult.collectLatest { newCommentResult ->
            when (newCommentResult) {
                is NetworkResult.Success -> {

                    if (newCommentResult.message.equals("کامنت با موفقیت ثبت شد")) {
                        navController.popBackStack()
                    }

                    loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("TAG", "newCommentResult error : ${newCommentResult.message}")
                    loading = false
                }

                is NetworkResult.Loading -> {
                }
            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(R.string.say_your_comment),
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


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.semiMedium,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            var checkValue by remember { mutableStateOf(false) }

            Checkbox(
                checked = checkValue,
                onCheckedChange = {
                    checkValue = it
                }
            )

            Text(
                text = stringResource(R.string.comment_anonymously),
                style = Typography.h4,
                color = MaterialTheme.colors.semiDarkText,
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.extraSmall
                    )
            )


        }


        Spacer(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )

        OutlinedButton(
            onClick = {
                loading = true

                val name = if (Constants.USER_NAME == "null")
                    context.getString(R.string.unknown_user)
                else
                    Constants.USER_NAME.replace("-","")

                val newComment = NewComment(
                    token = Constants.USER_TOKEN,
                    productId = productId,
                    title = commentTitle,
                    description = commentBody,
                    star = (sliderValue - 1).toInt(),
                    userName = name
                )

                if (newComment.title.isBlank()) {

                    loading = false

                    Toast.makeText(
                        c,
                        c.getString(R.string.comment_title_null),
                        Toast.LENGTH_LONG
                    ).show()

                } else if (newComment.description.isBlank()) {

                    loading = false

                    Toast.makeText(
                        c,
                        c.getString(R.string.comment_body_null),
                        Toast.LENGTH_LONG
                    ).show()

                } else if (newComment.star == 0) {

                    loading = false

                    Toast.makeText(
                        c,
                        c.getString(R.string.comment_star_null),
                        Toast.LENGTH_LONG
                    ).show()


                } else {
                    viewModel.setNewComment(newComment)
                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium)
        ) {

            if (loading) {
                OurLoading(height = 30.dp, isDark = true)
            } else {

                Text(
                    text = stringResource(R.string.set_new_comment),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.extraSmall),
                    textAlign = TextAlign.Center,
                    style = Typography.h4,
                    color = MaterialTheme.colors.semiDarkText
                )

            }


        }


    }

}
