package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.databinding.FormularioProdutoActivityBinding
import com.example.orgs.extensions.tentarCarregarImagemOuGif
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.FrmImagemDialog
import java.math.BigDecimal


class FormularioProdutoActivity : AppCompatActivity() {

    //  private val produto = CriarProduto()
//    private val dao = ProdutosDao()

    private val binding by lazy {
        FormularioProdutoActivityBinding.inflate(layoutInflater)
    }

    private var url: String? = null
    private var produtoId = 0L//
    private  var imageLoader: ImageLoader? = null
    private val produtoDao by lazy {
        AppDatabase.instaciaDB(this).produtoDao() //Criou a instacia

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        title = "Cadastra Produto"
        configuraBotaoSalvar()
        binding.activityFrmProdutoImagem.setOnClickListener {
            FrmImagemDialog(this)
                .mostra(url,imageLoader) { imagem, imageLo ->
                    url = imagem
                    imageLoader = imageLo
                    binding.activityFrmProdutoImagem.tentarCarregarImagemOuGif(url)
                }
        }

//        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let{
//            produtoCarregado ->
//            title = "Editar Produto"
//            produtoId = produtoCarregado.id
//            url = produtoCarregado.imagem
//            binding.fmrProdutoNome.setText(produtoCarregado.nome.toString())
//            binding.fmrProdutoDescricao.setText(produtoCarregado.descricao.toString())
//            binding.activityFrmProdutoImagem.tentarCarregarImagemOuGif(produtoCarregado.imagem)
//            binding.fmrProdutoValor.setText(produtoCarregado.valor.toPlainString())}


           tentaCarregarProduto()
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }


    fun preencherCampos(produto: Produto) {

            produtoId = produto.id
            url = produto.imagem
            binding.fmrProdutoNome.setText(produto.nome.toString())
            binding.fmrProdutoDescricao.setText(produto.descricao.toString())
            binding.activityFrmProdutoImagem.tentarCarregarImagemOuGif(produto.imagem)
            binding.fmrProdutoValor.setText(produto.valor.toPlainString())
    }

    override fun onResume() {
        super.onResume()
        buscarProduto()

    }

    private fun buscarProduto() {
        produtoDao.BuscarProdutoId(produtoId)?.let {
            title = "Editar Produto"
            preencherCampos(it)
        }
    }


    private fun configuraBotaoSalvar() {
        val btnSalvar = binding.frmProdutoBtnSalvarActivity

        btnSalvar.setOnClickListener {

            //val produto = CriarProduto()
            val Novoproduto = CriarProdutoBinding()
////            dao.add(produto)
//            Log.i("FrmProduto","onCreate : $produto")
//            Log.i("FrmProduto","onCreate : ${dao.buscaTodos()}")

//            if (produtoId > 0) {
//                produtoDao.alterar(produto)
//            } else {
//                produtoDao.salva(produto)
//
//            }
            produtoDao.salva(Novoproduto)
            finish()

        }
    }

    fun CriarProdutoBinding(): Produto {
        val campoNome = binding.fmrProdutoNome
        val campodescricao = binding.fmrProdutoDescricao
        val campoValor = binding.fmrProdutoValor

        val nome = campoNome.text.toString()
        val desc = campodescricao.text.toString()
        val valorEmCampo = campoValor.text.toString()

        var valor = if (valorEmCampo.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmCampo)
        }

        return Produto(
            id = produtoId,
            nome = nome,
            descricao = desc,
            valor = valor,
            imagem = url
        )
    }


}