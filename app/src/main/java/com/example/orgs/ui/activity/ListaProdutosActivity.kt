package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.ui.recyclerview.adpter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaProdutosActivity:AppCompatActivity() {
    private  val dao = ProdutosDao()
    private  val adapter = ListaProdutosAdapter(this, produtos = dao.buscaTodos())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_produtos_activity)
     //   val nome = findViewById<TextView>(R.id.textView)
      //  nome.text = "Cesta De Frutas Com outro nome"

        //    var descricao = findViewById<TextView>(R.id.textView2)
        //   descricao.text = "Laranja, manga e mança"

        //  var valor = findViewById<TextView>(R.id.textView3)
        //  valor.text = "19.99";
        CarregarRecyclerView()


    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
        ConfiguraFab()
    }

    private fun ConfiguraFab() {
        val botaoAdd =
            findViewById<FloatingActionButton>(R.id.lista_produto_floatingActionButton) // declaro uma variavel o botão de execução

        botaoAdd.setOnClickListener { //Crio um envito click
            val intent = Intent(
                this,
                formulario_produto_activity::class.java
            ) // crio uma intent ussando o contexto e class que irei usar
            startActivity(intent) // e inicio ela usando o startActivity
        }
    }

    private fun CarregarRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.lista_produto_recyclerView)

    //    recyclerView.adapter = ListaProdutosAdapter(this, produtos = listOf(

        //    Produto(nome = "Teste1", descricao = "Descricao 1", valor = BigDecimal("19.99")),
        //    Produto(nome = "Teste2", descricao = "Descricao2 ", valor = BigDecimal("20.99")),
        //    Produto(nome = "Teste3", descricao = "Descricao3 ", valor = BigDecimal("1.99"))
        //   ))
        val dao = ProdutosDao()
        recyclerView.adapter = adapter
        Log.i("MainActivity", "onCreate :${dao.buscaTodos()} ")
        recyclerView.layoutManager = LinearLayoutManager(this);
    }

}