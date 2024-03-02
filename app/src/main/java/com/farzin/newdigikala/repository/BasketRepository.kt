package com.farzin.newdigikala.repository

import com.farzin.newdigikala.data.db.CartDao
import com.farzin.newdigikala.data.model.basket.CartItem
import com.farzin.newdigikala.data.model.basket.CartStatus
import com.farzin.newdigikala.data.model.home.StoreProduct
import com.farzin.newdigikala.data.remote.BaseApiResponse
import com.farzin.newdigikala.data.remote.BasketApiInterface
import com.farzin.newdigikala.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BasketRepository @Inject constructor(
    private val api: BasketApiInterface,
    private val dao: CartDao,
) : BaseApiResponse() {

    val currentCartItems = dao.getAllItems(CartStatus.CURRENT_CART)
    val nextCartItems = dao.getAllItems(CartStatus.NEXT_CART)

    val currentCartItemsCount = dao.getCartItemsCount(CartStatus.CURRENT_CART)
    val nextCartItemsCount = dao.getCartItemsCount(CartStatus.NEXT_CART)


    suspend fun getSuggestedItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSuggestedItems()
        }


    suspend fun insertCartItem(cart: CartItem) {
        dao.insertCartItem(cart)
    }

    suspend fun removeFromCart(cart: CartItem) {
        dao.removeFromCart(cart)
    }

    suspend fun changeCartItemStatus(id: String, newStatus: CartStatus) {
        dao.changeStatusCart(id, newStatus)
    }

    suspend fun changeCartItemCount(id: String, newCount: Int) {
        dao.changeCountCartItem(id, newCount)
    }

    fun isItemInBasket(id: String): Flow<Boolean> =
        dao.isItemInBasket(id)

    fun getItemCount(id: String): Flow<Int> =
        dao.getItemCount(id)



}