package com.inflames.curenciesviewmodel.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.textfield.TextInputEditText
import com.inflames.curenciesviewmodel.R
import com.inflames.curenciesviewmodel.currencylistscreen.adapter.CryptoListAdapter
import com.inflames.curenciesviewmodel.enums.CryptoApiStatus
import com.inflames.curenciesviewmodel.model.CryptoModel
import java.text.NumberFormat
import java.util.*


@BindingAdapter("showAnimation")
fun showAnimation(
    lottieAnimationView: LottieAnimationView,
    apiStatus: CryptoApiStatus?,
) {
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
        CryptoApiStatus.DONE -> {
            lottieAnimationView.visibility = View.GONE
            lottieAnimationView.cancelAnimation()
        }
        else -> lottieAnimationView.visibility = View.GONE
    }
}

@BindingAdapter("imageUrl")
fun downloadImage(imageView: ImageView, imageUrl: String?) {


    imageUrl?.let { image ->

        val imageLoader = ImageLoader.Builder(imageView.context)
            .componentRegistry { add(SvgDecoder(imageView.context)) }
            .build()

        val request = ImageRequest.Builder(imageView.context)
            .crossfade(true)
            .crossfade(300)
            .placeholder(R.drawable.animate_loading)
            .error(R.drawable.ic_broken_image)
            .data(image)
            .target(imageView)
            .build()

        imageLoader.enqueue(request)


    }
}


@BindingAdapter("numberFormat")
fun numberFormat(textView: TextView, price: String?) {

    price?.let {
        val dPrice = price.toDouble()
        val formattedPrice = NumberFormat.getCurrencyInstance(Locale.US).format(dPrice)
        textView.text = formattedPrice
    }

}


@BindingAdapter("bindList")
fun bindList(recyclerView: RecyclerView, cryptoList: List<CryptoModel>?) {

    if (!cryptoList.isNullOrEmpty()) {
        val adapter = recyclerView.adapter as CryptoListAdapter
        adapter.submitList(cryptoList)
    }


}

@BindingAdapter("onRefreshListener")
fun onRefreshListener(
    swipeRefreshLayout: SwipeRefreshLayout,
    refresh: () -> Unit
) {

    swipeRefreshLayout.setOnRefreshListener {
        swipeRefreshLayout.isRefreshing = false
        refresh.invoke()
    }


}


@BindingAdapter("description")
fun description(editText: TextInputEditText, description: String?) {

    description?.let {
        editText.visibility = View.VISIBLE
        editText.setText(it)
        editText.isEnabled = false
    }
}

@BindingAdapter("isHaveUrl")
fun isHaveUrl(imageView: ImageView, url: String?) {

    if (url==null){
        imageView.visibility = View.GONE
    }else {
        if (url == "") {
            imageView.visibility = View.GONE
        } else {
            imageView.visibility = View.VISIBLE
        }
    }



}