package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.orgs.databinding.ActivityViewProdutoDadosBinding
import com.example.orgs.databinding.ProdutoItemBinding.inflate
import com.example.orgs.extensions.formataParaMoedaBrasileira
import com.example.orgs.extensions.tentarCarregarImagemOuGif
import com.example.orgs.model.Produto

class Activity_View_Produto_Dados: AppCompatActivity() {
    private val binding by lazy {
        ActivityViewProdutoDadosBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title ="detalhes do produto"
        tentaCarregarProduto()
    }


    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencheCampos(produtoCarregado)
        } ?: finish()
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