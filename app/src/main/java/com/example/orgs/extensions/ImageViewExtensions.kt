package com.example.orgs.extensions


import android.widget.ImageView
import coil.ImageLoader
import coil.load
import com.example.orgs.R


fun ImageView.tentarCarregarImagemOuGif(url:String? = null, imageLoader: ImageLoader? = null){
//    if( url !=null){
//        if(imageLoader != null){
//            load(url,imageLoader = imageLoader!!){
//                fallback(R.drawable.erro)
//                error(R.drawable.erro)
//                placeholder(R.drawable.placeholder)
//        }
//    }

    if(url != null || url != ""){
        if(imageLoader == null){
            load(url){
                fallback(R.drawable.erro)
                error(R.drawable.erro)
                placeholder(R.drawable.placeholder)

            }
        }else{
            load(url,imageLoader){
                fallback(R.drawable.erro)
                error(R.drawable.erro)
                placeholder(R.drawable.placeholder)
            }
        }

    }
}