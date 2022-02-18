package com.inflames.curenciesviewmodel.util

import android.view.View
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.inflames.curenciesviewmodel.enums.CryptoApiStatus


@BindingAdapter("showAnimation")
fun showAnimation(lottieAnimationView: LottieAnimationView, apiStatus: CryptoApiStatus?) {


    when (apiStatus) {
        CryptoApiStatus.ERROR -> {
            lottieAnimationView.visibility = View.VISIBLE
            lottieAnimationView.setAnimation("network-error.json")
            lottieAnimationView.playAnimation()

        }
        CryptoApiStatus.LOADING -> {
            lottieAnimationView.visibility = View.VISIBLE
            lottieAnimationView.setAnimation("loading.json")
            lottieAnimationView.playAnimation()

        }
        else -> lottieAnimationView.visibility = View.GONE

    }
}