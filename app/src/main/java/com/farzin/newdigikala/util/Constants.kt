package com.farzin.newdigikala.util

import com.farzin.newdigikala.BuildConfig

object Constants {
    const val ENGLISH_LANG = "en"
    const val PERSIAN_LANG = "fa"
    const val DATASTORE_NAME = "DIGIKALA_DATA_STORE"
    const val BASE_URL = "https://truelearn-digikala.iran.liara.run/api/v1/"
    const val TIMEOUT_IN_SECOND: Long = 60
    const val API_KEY = BuildConfig.X_API_KEY
    const val KEY = BuildConfig.KEY
    const val IV = BuildConfig.IV
    const val SHOPPING_CART_TABLE = "shopping_cart"
    const val FAV_LIST_TABLE = "fav_list"
    const val DATABASE_NAME = "digikala_db"
    var USER_LANGUAGE = "USER_LANGUAGE"
    var USER_TOKEN = "USER_TOKEN"
    var USER_ID = "USER_ID"
    var USER_PHONE = "USER_PHONE"
    var USER_PASSWORD = "USER_PASSWORD"
    var USER_NAME = "USER_NAME"


    const val DIGIJET_URL = "https://www.digikalajet.com/user/address"
    const val AUCTION_URL =
        "https://www.digistyle.com/sale-landing/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=style&promo_name=style&promo_position=circle_badge"
    const val DIGIPAY_URL =
        "https://www.digikala.com/my-digipay/?promo_name=my-digipay&promo_position=circle_badge"
    const val PINDO_URL =
        "https://www.pindo.ir/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=pindo&promo_name=pindo&promo_position=circle_badge"
    const val SHOPPING_URL =
        "https://www.digikala.com/landings/village-businesses/?promo_name=boomi-landing&promo_position=circle_badge"
    const val GIFT_CARD_URL =
        "https://www.digikala.com/landing/gift-card-landing/?promo_name=gift_landing&promo_position=circle_badge"
    const val DIGIPLUS_URL =
        "https://www.digikala.com/plus/landing/?promo_name=plus&promo_position=circle_badge"
    const val MORE_URL =
        "https://www.digikala.com/mehr/?promo_name=mehr&promo_position=circle_badge"


    const val DIGI_FAQ = "https://www.digikala.com/faq/"
    const val DIGI_PRIVACY = "https://www.digikala.com/page/privacy/"
    const val DIGI_TERMS = "https://www.digikala.com/page/terms/"
    const val DIGI_TURLEARN = "https://truelearn.ir/"
    const val DIGI_BUG = "https://www.digikala.com/page/bug-report/"
    const val DIGI_SCORE = "https://cafebazaar.ir/app/com.digikala"

    const val DIGI_WALLET = "https://www.mydigipay.com/"
    const val DIGI_CLUB = "https://www.digikala.com/digiclub/"
    const val TRUELEARN_CONTACT_US = "https://truelearn.ir/contact/"


    const val PRODUCT_COMMENTS = "PRODUCT_COMMENTS"
    const val USER_COMMENTS = "USER_COMMENTS"


    var isFromPurchase = false
    var afterPurchaseUrl = ""
    var purchaseOrderId = ""
    var purchasePrice = ""




}