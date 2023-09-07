package com.example.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter // Essa função é para pegar um valor do banco e converter em BigDeciaml
    fun deDouble(valor:Double?) : BigDecimal{
        return valor?.let {
           BigDecimal(valor.toString())
        }?: BigDecimal.ZERO

    }


    @TypeConverter // Esse Metodo irá converter um bigDecimal em double
    fun bigDecimalParaDouble(valor:BigDecimal?): Double?{
        return valor?.let { valor.toDouble() }
    }
}