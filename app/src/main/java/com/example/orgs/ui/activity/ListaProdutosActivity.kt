package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.databinding.ActivityListaProdutosActivityBinding
import com.example.orgs.databinding.ActivityViewProdutoDadosBinding

import com.example.orgs.ui.recyclerview.adpter.ListaProdutosAdapter

class ListaProdutosActivity:AppCompatActivity() {
    private  val dao = ProdutosDao()
    private  val adapter = ListaProdutosAdapter(this, produtos = dao.buscaTodos())
    private  lateinit var binding : ActivityListaProdutosActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProdutosActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CarregarRecyclerView()
        configuraFab()


    }


    private fun configuraFab() {
        val fab = binding.listaProdutoFloatingActionButton
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
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



