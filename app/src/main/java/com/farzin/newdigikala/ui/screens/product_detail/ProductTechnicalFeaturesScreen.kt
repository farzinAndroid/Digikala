package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.ui.theme.Typography
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.grayCategory
import com.farzin.newdigikala.ui.theme.searchBarBg
import com.farzin.newdigikala.ui.theme.semiDarkText
import com.farzin.newdigikala.ui.theme.spacing
import org.json.JSONObject

@Composable
fun ProductTechnicalFeaturesScreen(
    navController: NavController,
    jsonString: String,
) {


    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(24.dp)

            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.technical_specifications),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
            )

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(MaterialTheme.colors.searchBarBg)
        )

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.semiMedium),
                text = stringResource(id = R.string.specifications),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
            )

            val jsonObject = JSONObject(jsonString)
            val keys = jsonObject.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                val value = jsonObject.get(key)

                TechnicalFeaturesRow(key = key, value = value.toString())
            }
        }
    }

}


@Composable
private fun TechnicalFeaturesRow(key: String, value: String) {


    Row {

        Column(
            modifier = Modifier
                .weight(0.35f)
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                )
        ) {

            Text(
                text = key,
                style = Typography.h6,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.semiDarkText
            )

        }


        Column(
            modifier = Modifier
                .weight(0.65f)
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                )
        ) {

            Text(
                text = value,
                style = Typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText
            )


            Spacer(
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.biggerSmall)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colors.grayCategory)
            )


        }


    }


}
