package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityViewProdutoDadosBinding
import com.example.orgs.databinding.FormularioProdutoActivityBinding
import com.example.orgs.extensions.formataParaMoedaBrasileira
import com.example.orgs.extensions.tentarCarregarImagemOuGif
import com.example.orgs.model.Produto

class Activity_View_Produto_Dados: AppCompatActivity() {
    private val binding by lazy {
        ActivityViewProdutoDadosBinding.inflate(layoutInflater)
    }

    private  lateinit var produto: Produto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title ="detalhes do produto"
        tentaCarregarProduto()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Esse metodo serve para colocar o menu na activity
        menuInflater.inflate(R.menu.menu_detalhes_produto,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Esse metodo serve para pegar informação do Menu item Selecionado.
       if (::produto.isInitialized){ // Se o produto foi inicializado certo e for diferente de nullo
           val db = AppDatabase.instaciaDB(this) // irei criar uma instacia de db
           var produtoDao = db.produtoDao() // pegar o produtoDao

           when(item.itemId){ //
               R.id.menuRemover ->{
                   produtoDao.remover(produto) // removo o produto
                   finish() // volto para tela
               }

               R.id.menuEdit ->{ // Crio uma intente que irá abrir a tela do formulario e passar os dados do produto.
                Intent(this,formulario_produto_activity::class.java).apply {
                    putExtra(CHAVE_PRODUTO,produto)
                    startActivity(this)
                }

               }
           }
       }


        return super.onOptionsItemSelected(item)
    }
    private fun tentaCarregarProduto() {
        // tentativa de buscar o produto se ele existir,
        // caso contrário, finalizar a Activity
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            produto = produtoCarregado
            preencheCampos(produtoCarregado)
        } ?: finish()
    }
    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityViewProdutoDadosImagemView.tentarCarregarImagemOuGif(produtoCarregado.imagem)
            activityViewProdutoDadosTitulo.text = produtoCarregado.nome
            activityViewProdutoDadosTexto.text = produtoCarregado.descricao
            activityViewProdutoDadosValor.text =
                produtoCarregado.valor.toPlainString()
        }
    }


}