package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityViewProdutoDadosBinding
import com.example.orgs.extensions.formataParaMoedaBrasileira
import com.example.orgs.extensions.tentarCarregarImagemOuGif
import com.example.orgs.model.Produto
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Activity_View_Produto_Dados : AppCompatActivity() {
    private var produtoId: Long = 0L
    private var produto: Produto? = null

    private val binding by lazy {
        ActivityViewProdutoDadosBinding.inflate(layoutInflater)
    }

    private val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao() // irei criar uma instacia de db
//        var produtoDao = db.produtoDao() // pegar o produtoDao
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Detalhes do Produto"
        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        buscaProduto()

    }

    private fun buscaProduto() {
        lifecycleScope.launch {
            produtoDao.BuscarProdutoId(produtoId).collect { produtoEncontrado ->
                produto = produtoEncontrado
                produto?.let {
                    preencheCampos(it)
                } ?: finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Esse metodo serve para colocar o menu na activity
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Esse metodo serve para pegar informação do Menu item Selecionado.
        // Se o produto foi inicializado certo e for diferente de nullo


        when (item.itemId) { //
            R.id.menuRemover -> {

                lifecycleScope.launch {
                    produto?.let {
                        produtoDao.remover(it) // removo o produto
                    }

                    finish() // volto para tela
                }
            }


            R.id.menuEdit -> { // Crio uma intente que irá abrir a tela do formulario e passar os dados do produto.
//                    Intent(this, formulario_produto_activity::class.java).apply {
//                        putExtra(CHAVE_PRODUTO, produto)
//                        startActivity(this)
//                    }

                Intent(this, FormularioProdutoActivity::class.java).apply {
                    putExtra(CHAVE_PRODUTO_ID, produtoId)
                    startActivity(this)
                }

            }

        }


        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        // tentativa de buscar o produto se ele existir,
        // caso contrário, finalizar a Activity
//        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
////            produto = produtoCarregado
//            produtoId = produtoCarregado.id
//            preencheCampos(produtoCarregado)

        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)

    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityViewProdutoDadosImagemView.tentarCarregarImagemOuGif(produtoCarregado.imagem)
            activityViewProdutoDadosTitulo.text = produtoCarregado.nome
            activityViewProdutoDadosTexto.text = produtoCarregado.descricao
            activityViewProdutoDadosValor.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
        }
    }


}