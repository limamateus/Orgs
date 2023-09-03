package com.example.orgs.dao

import com.example.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun add(produto: Produto){

        Companion.produtos.add(produto)

    }

    fun buscaTodos():List<Produto>{

      return Companion.produtos.toList()
    }

    companion object {
        private val produtos = mutableSetOf<Produto>(
            Produto(
                nome = "Salada De Frutas",
                descricao = "Tudo Que eu não gosto",
                valor = BigDecimal("19.78"),
                imagem = null,
               // imageLoader = null
            )

        )
    }

}