package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.navigation.Screen
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.extraSmall
import com.farzin.newdigikala.ui.theme.grayCategory
import com.farzin.newdigikala.ui.theme.settingArrow
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.ui.theme.unSelectedBottomBar

@Composable
fun ProductDescriptionSection(
    navController: NavController,
    description: String,
    technical: String,
) {


    var isHaveDescription by remember { mutableStateOf(true) }

    if (description.isBlank())
        isHaveDescription = false

    var isHaveTechnical by remember { mutableStateOf(true) }

    if (technical == "null")
        isHaveTechnical = false


    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.small)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.extraSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.product_desc),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
            )

        }



        if (isHaveTechnical) {
            Spacer(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colors.grayCategory)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .clickable {
                        navController.navigate(Screen.TechnicalFeatures.withArgs(technical))
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(R.string.technical_specifications),
                    color = MaterialTheme.colors.darkText,
                    fontWeight = FontWeight.Bold,
                    style = Typography.h5
                )

                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.settingArrow
                )

            }
        }







        if (isHaveDescription) {
            Spacer(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colors.grayCategory)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .clickable {
                        navController.navigate(Screen.ProductDescription.withArgs(description))
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(R.string.product_introduce),
                    color = MaterialTheme.colors.darkText,
                    fontWeight = FontWeight.Bold,
                    style = Typography.h5
                )

                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.settingArrow
                )

            }

        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.extraSmall
                ),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(
                text = stringResource(R.string.product_desc_feedback),
                style = Typography.extraSmall,
                color = MaterialTheme.colors.unSelectedBottomBar,
                modifier = Modifier
                    .clickable { }
            )

            Image(
                painter = painterResource(R.drawable.info),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)

            )

        }


    }
}