package com.farzin.newdigikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.farzin.newdigikala.data.model.basket.CartDetails
import com.farzin.newdigikala.data.model.basket.CartItem
import com.farzin.newdigikala.data.model.basket.CartStatus
import com.farzin.newdigikala.data.model.home.StoreProduct
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.repository.BasketRepository
import com.farzin.newdigikala.ui.screens.basket.BasketScreenState
import com.farzin.newdigikala.util.DigitHelper.calculateDiscount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BasketViewModel @Inject constructor(private val repo: BasketRepository) : ViewModel() {


    val suggestedList = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val cartDetail = MutableStateFlow(CartDetails(0, 0, 0, 0))
    val ourCurrentCartItems = MutableStateFlow<List<CartItem>>(emptyList())


    private val _currentCartItems: MutableStateFlow<BasketScreenState<List<CartItem>>> =
        MutableStateFlow(BasketScreenState.Loading)
    val currentCartItems: StateFlow<BasketScreenState<List<CartItem>>> = _currentCartItems

    private val _nextCartItems: MutableStateFlow<BasketScreenState<List<CartItem>>> =
        MutableStateFlow(BasketScreenState.Loading)
    val nextCartItems: StateFlow<BasketScreenState<List<CartItem>>> = _nextCartItems


    val currentCartItemsCount = repo.currentCartItemsCount
    val nextCartItemsCount = repo.nextCartItemsCount

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                repo.currentCartItems.collectLatest { cartItems ->
                    _currentCartItems.emit(BasketScreenState.Success(cartItems))
                    ourCurrentCartItems.emit(cartItems)

                }
            }
            launch {
                repo.currentCartItems.collectLatest { cartItems ->
                    calculateCartDetails(cartItems)

                }
            }
            launch {
                repo.nextCartItems.collectLatest { nextCartItems ->
                    _nextCartItems.emit(BasketScreenState.Success(nextCartItems))
                }
            }
        }
    }


    private fun calculateCartDetails(items: List<CartItem>) {
        var totalCount = 0
        var totalPrice = 0L
        var totalDiscount = 0L
        var payablePrice = 0L
        items.forEach { item ->
            totalPrice += item.price * item.count
            payablePrice += calculateDiscount(item.price, item.discountPercent) * item.count
            totalCount += item.count
        }
        totalDiscount = totalPrice - payablePrice
        cartDetail.value = CartDetails(totalCount, totalPrice, totalDiscount, payablePrice)
    }


    fun getSuggestedItems() {
        viewModelScope.launch {
            suggestedList.emit(repo.getSuggestedItems())
        }
    }


    fun insertCartItem(item: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertCartItem(item)
        }
    }


    fun removeCartItem(item: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.removeFromCart(item)
        }
    }

    fun changeCartItemCount(id: String, newCount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.changeCartItemCount(id, newCount)
        }
    }

    fun changeCartItemStatus(id: String, newStatus: CartStatus) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.changeCartItemStatus(id, newStatus)
        }
    }

    fun deleteAllCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAllCartItems()
        }
    }

    fun isItemInBasket(id: String): Flow<Boolean> =
            repo.isItemInBasket(id)

    fun getItemCount(id: String): Flow<Int> =
            repo.getItemCount(id)




}