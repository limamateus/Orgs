package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto") // Comanda para buscar
    fun buscaTodos() : List<Produto>

    @Insert // Comando Para Salvar
    fun salva( vararg produto: Produto)

}