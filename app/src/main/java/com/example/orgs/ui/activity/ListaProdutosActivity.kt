package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.room.Room
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.database.AppDatabase.Companion.instaciaDB
import com.example.orgs.databinding.ActivityListaProdutosActivityBinding
import com.example.orgs.databinding.ActivityViewProdutoDadosBinding
import com.example.orgs.model.Produto

import com.example.orgs.ui.recyclerview.adpter.ListaProdutosAdapter
import java.math.BigDecimal

class ListaProdutosActivity:AppCompatActivity() {
    private  val adapter = ListaProdutosAdapter(this)

    private val binding by lazy {
        ActivityListaProdutosActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        CarregarRecyclerView()
        configuraFab()

    }

    fun showPopup(v: View) {
        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_detalhes_produto, popup.menu)
        popup.show()
    }

//    override fun onMenuItemClick(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.menuEdit -> {
//               // archive(item)
//                true
//            }
//            R.id.menuRemover -> {
//               // Remover
//                true
//            }
//            else -> false
//        }
//    }
    override fun onResume() {
        super.onResume()

        val db = instaciaDB(this)
        val produtoDao = db.produtoDao()

        adapter.atualiza(produtoDao.buscaTodos())
       // adapter.atualiza(dao.buscaTodos())
    }
    private fun configuraFab() {
        val fab = binding.listaProdutoFloatingActionButton
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
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
        val intent = Intent(this, formulario_produto_activity::class.java)
        startActivity(intent)
    }


    private fun CarregarRecyclerView(){
        val recyclerView = binding.listaProdutoRecyclerView
        recyclerView.adapter = adapter

        adapter.quandoClicaNoItem = {
            val intent = Intent(
                this, Activity_View_Produto_Dados::class.java
            ).apply{
                putExtra(CHAVE_PRODUTO, it)

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



