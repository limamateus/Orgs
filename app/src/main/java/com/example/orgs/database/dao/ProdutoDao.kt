package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto") // Comanda para buscar
    fun buscaTodos() : List<Produto>

    @Insert // Comando Para Salvar
    fun salva( vararg produto: Produto)


    @Delete //  Comando para deletar
    fun remover (vararg  produto: Produto);

    @Update // Comando para Atualizar o produto
    fun alterar(produto: Produto)

}