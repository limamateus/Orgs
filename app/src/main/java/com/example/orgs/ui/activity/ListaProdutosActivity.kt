package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.lifecycleScope
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityListaProdutosActivityBinding
import com.example.orgs.model.Produto
import com.example.orgs.ui.recyclerview.adpter.ListaProdutosAdapter
import kotlinx.coroutines.launch


class ListaProdutosActivity:AppCompatActivity() {
    private  val adapter = ListaProdutosAdapter(this)

    private val binding by lazy {
        ActivityListaProdutosActivityBinding.inflate(layoutInflater)
    }

    private  val produtoDao by lazy {
       AppDatabase.instancia(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        CarregarRecyclerView()
        configuraFab()

        lifecycleScope.launch {
            produtoDao.buscaTodos().collect{
                produtos ->
                adapter.atualiza(produtos)
            }
        }


    }




    private fun configuraFab() {
        val fab = binding.listaProdutoFloatingActionButton
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    fun showPopup(v: View) {
        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.menuInflater
         inflater.inflate(R.menu.menu_detalhes_produto, popup.menu)

        popup.show()


    }



            //findViewById<FloatingActionButton>(R.id.lista_produto_floatingActionButton) // declaro uma variavel o botão de execução
//
//        botaoAdd.setOnClickListener { //Crio um envito click
//            val intent = Intent(
//                this,
//                formulario_produto_activity::class.java
//            ) // crio uma intent ussando o contexto e class que irei usar
//            startActivity(intent) // e inicio ela usando o startActivity
//        }


    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Esse metodo serve para colocar o menu na activity
        menuInflater.inflate(R.menu.menu_ordernacao_de_produtos, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lifecycleScope.launch {
            val produtoOrdenado:  List<Produto>? = when(item.itemId){

                R.id.menu_lista_produtos_ordenar_nome_asc ->
                    produtoDao.buscaTodosOrdenadorPorNomeAsc()
                R.id.menu_lista_produtos_ordenar_nome_desc ->
                    produtoDao.buscaTodosOrdenadorPorNomeDesc()
                R.id.menu_lista_produtos_ordenar_descricao_asc ->
                    produtoDao.buscaTodosOrdenadorPorDescricaoAsc()
                R.id.menu_lista_produtos_ordenar_valor_asc ->
                    produtoDao.buscaTodosOrdenadorPorValorAsc()
                R.id.menu_lista_produtos_ordenar_valor_desc ->
                    produtoDao.buscaTodosOrdenadorPorValorDesc()

                else -> null
            }
            produtoOrdenado?.let { adapter.atualiza(it) }
        }



        return super.onOptionsItemSelected(item)
    }


    private fun CarregarRecyclerView(){
        val recyclerView = binding.listaProdutoRecyclerView
        recyclerView.adapter = adapter


        adapter.quandoClicaNoItem = {
            val intent = Intent(
                this, Activity_View_Produto_Dados::class.java
            ).apply{
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
            Log.i("CarregarRecyclerView","o Problema esta no CarregarRecyclerView")
        }



//        val recyclerView = findViewById<RecyclerView>(R.id.lista_produto_recyclerView)

        //    recyclerView.adapter = ListaProdutosAdapter(this, produtos = listOf(

        //    Produto(nome = "Teste1", descricao = "Descricao 1", valor = BigDecimal("19.99")),
        //    Produto(nome = "Teste2", descricao = "Descricao2 ", valor = BigDecimal("20.99")),
        //    Produto(nome = "Teste3", descricao = "Descricao3 ", valor = BigDecimal("1.99"))
        //   ))
//        val dao = ProdutosDao()
//        recyclerView.adapter = adapter
//        Log.i("MainActivity", "onCreate :${dao.buscaTodos()} ")
//        recyclerView.layoutManager = LinearLayoutManager(this);
        }
    }



