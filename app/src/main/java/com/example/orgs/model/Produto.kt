package com.example.orgs.model

import android.os.Parcelable
import coil.ImageLoader
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal


@Parcelize
data class Produto (

    val nome:String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null,
    //val imageLoader: ImageLoader? = null
        ) : Parcelable

