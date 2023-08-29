package com.example.orgs.model

import coil.ImageLoader
import java.math.BigDecimal

data class Produto (

    val nome:String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null,
    val imageLoader: ImageLoader? = null
        )