package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.newdigikala.data.model.profile.FavItem
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.viewmodel.FavoriteListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DisplayFavoriteToggleButton(
    favItem: FavItem,
    favoriteListViewModel: FavoriteListViewModel = hiltViewModel(),
) {


    var checked by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        favoriteListViewModel.isFavItemExist(favItem.id).collectLatest {
            checked = it
        }
    }

    IconToggleButton(
        checked = checked,
        onCheckedChange = {
            checked = !checked

            if (checked) {
                favoriteListViewModel.addFavorite(favItem)
            } else {
                favoriteListViewModel.deleteFavorite(favItem)
            }

        }
    ) {

        val transition = updateTransition(checked, "icon transition")
        val tint by transition.animateColor(label = "icon transition") { isChecked ->
            if (isChecked) Color.Red else MaterialTheme.colors.darkText
        }

        Icon(
            imageVector = if (checked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "",
            modifier = Modifier
                .size(25.dp),
            tint = tint
        )
    }


}