package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto") // Comanda para buscar
    fun buscaTodos() : List<Produto>
    @Query("SELECT * FROM Produto ORDER BY nome ASC")
    fun buscaTodosOrdenadorPorNomeAsc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY nome DESC")
    fun buscaTodosOrdenadorPorNomeDesc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY descricao ASC")
    fun buscaTodosOrdenadorPorDescricaoAsc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY descricao DESC")
    fun buscaTodosOrdenadorPorDescricaoDesc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY valor ASC")
    fun buscaTodosOrdenadorPorValorAsc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY valor DESC")
    fun buscaTodosOrdenadorPorValorDesc(): List<Produto>
    @Insert (onConflict = OnConflictStrategy.REPLACE)// Comando Para Salvar ou alterar coso já tenha o Id
    fun salva( vararg produto: Produto)


    @Delete //  Comando para deletar
    fun remover (vararg  produto: Produto);

    @Update // Comando para Atualizar o produto
    fun alterar(produto: Produto)


    @Query ("SELECT * FROM Produto WHERE id = :id") // Comanda para buscar algo especifico
    fun BuscarProdutoId(id:Long) : Produto
}