package com.example.orgs.extensions


import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

public fun BigDecimal.formataParaMoedaBrasileira(): String {
    val formatador: NumberFormat = NumberFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return formatador.format(this)
}