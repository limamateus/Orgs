package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import coil.ImageLoader
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.databinding.FormularioProdutoActivityBinding
import com.example.orgs.extensions.formataParaMoedaBrasileira
import com.example.orgs.extensions.tentarCarregarImagemOuGif
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.FrmImagemDialog
import java.math.BigDecimal


class formulario_produto_activity: AppCompatActivity() {

  //  private val produto = CriarProduto()
//    private val dao = ProdutosDao()

    private val binding by lazy {

        FormularioProdutoActivityBinding.inflate(layoutInflater)
    }

    private var url: String? = null
    private var  produtoId = 0L
    var imageLoader: ImageLoader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        configuraBotaoSalvar()
        val bindingImagem = FormularioImagemBinding.inflate(layoutInflater)
        binding.activityFrmProdutoImagem.setOnClickListener {

            FrmImagemDialog(this).mostra(url,imageLoader){
                    imagem, imageL ->
                url = imagem
                imageLoader = imageL
                binding.activityFrmProdutoImagem.tentarCarregarImagemOuGif(url,imageLoader)
            }


        }

        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let{
            produtoCarregado ->
            title = "Editar Produto"
            produtoId = produtoCarregado.id
            url = produtoCarregado.imagem
            binding.fmrProdutoNome.setText(produtoCarregado.nome.toString())
            binding.fmrProdutoDescricao.setText(produtoCarregado.descricao.toString())
            binding.activityFrmProdutoImagem.tentarCarregarImagemOuGif(produtoCarregado.imagem)
            binding.fmrProdutoValor.setText(produtoCarregado.valor.toPlainString())


        }
    }

    private fun configuraBotaoSalvar(){
        val  btnSalvar = binding.frmProdutoBtnSalvarActivity

        btnSalvar.setOnClickListener {

            //val produto = CriarProduto()
            val produto = CriarProdutoBinding()
////            dao.add(produto)
//            Log.i("FrmProduto","onCreate : $produto")
//            Log.i("FrmProduto","onCreate : ${dao.buscaTodos()}")

            val db = AppDatabase.instaciaDB(this) //Criou a instacia

            val produtoDao = db.produtoDao()

            if(produtoId > 0 ){
                produtoDao.alterar(produto)
            }else{
                produtoDao.salva( produto )

            }
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

        var valor = if(valorEmCampo.isBlank()){
            BigDecimal.ZERO
        }else{
            BigDecimal(valorEmCampo)
        }

        return Produto(id =produtoId, nome =nome, descricao = desc, valor = valor, imagem = url)
    }



}