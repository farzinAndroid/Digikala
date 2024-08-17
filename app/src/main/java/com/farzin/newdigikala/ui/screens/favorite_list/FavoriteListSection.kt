package com.farzin.newdigikala.ui.screens.favorite_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.profile.FavItem
import com.farzin.newdigikala.ui.theme.digikalaRed
import com.farzin.newdigikala.ui.theme.roundedShape
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.util.DigitHelper
import com.farzin.newdigikala.viewmodel.FavoriteListViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteListSection(
    navController: NavController,
    favoriteListViewModel: FavoriteListViewModel = hiltViewModel(),
) {


    val favList by favoriteListViewModel.allFavoriteItems.collectAsState(emptyList())
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    var selectedItem by remember {
        mutableStateOf(FavItem("","","",0L,0,"",0.0))
    }


    ModalBottomSheetLayout(
        sheetContent = {
            Column(
                modifier = Modifier
                    .height(120.dp)
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerSmall))

                Text(
                    text = stringResource(R.string.sure_to_remove_fav_item),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.biggerSmall)
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedButton(
                        onClick = {
                            favoriteListViewModel.deleteFavorite(selectedItem)
                            scope.launch {
                                if (sheetState.isVisible){
                                    sheetState.hide()
                                }
                            }
                        },
                        border = BorderStroke(1.dp, color = MaterialTheme.colors.digikalaRed),
                        shape = MaterialTheme.roundedShape.semiSmall,
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.remove_from_list),
                            color = MaterialTheme.colors.digikalaRed,
                            style = MaterialTheme.typography.h3
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            scope.launch {
                                if (sheetState.isVisible){
                                    sheetState.hide()
                                }
                            }
                        },
                        border = BorderStroke(1.dp, color = MaterialTheme.colors.digikalaRed),
                        shape = MaterialTheme.roundedShape.semiSmall,
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            color = MaterialTheme.colors.digikalaRed,
                            style = MaterialTheme.typography.h3
                        )
                    }

                }

            }
        },
        sheetState = sheetState,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CountFavoriteSection(itemCount = favList.size)

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.small)
                ) {
                    if(favList.isEmpty()){
                        item { EmptyFavoriteListContent() }
                    }else{
                        items(favList){ item ->
                            FavoriteItemCard(
                                navController = navController,
                                favItem = item,
                                coroutineScope =scope,
                                modalSheetState = sheetState
                            ) {
                                selectedItem = it
                            }
                        }
                    }
                }

            }
        }
    )
}

@Composable
fun CountFavoriteSection(itemCount: Int) {

    Row(
        Modifier
            .padding(
                top = MaterialTheme.spacing.biggerSmall,
                bottom = MaterialTheme.spacing.biggerMedium
            )
    ) {

        Text(
            text = stringResource(id = R.string.fav_products),
            color = Color.Gray,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.extraSmall)
                .weight(0.9f)
        )
        Text(
            text = "${DigitHelper.digitByLang(itemCount.toString())} ${stringResource(id = R.string.product)}",
            color = Color.Gray,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.weight(0.1f)
        )


    }

}