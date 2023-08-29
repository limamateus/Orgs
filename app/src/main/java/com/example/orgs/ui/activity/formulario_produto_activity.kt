package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.databinding.FormularioProdutoActivityBinding
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

    private  var imageLoader: ImageLoader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        configuraBotaoSalvar()
        val bindingImagem = FormularioImagemBinding.inflate(layoutInflater)
        binding.activityFrmProdutoImagem.setOnClickListener {

            FrmImagemDialog(this).mostra({
                    imagem, imageL ->
                url = imagem
                imageLoader = imageL
                binding.activityFrmProdutoImagem.tentarCarregarImagemOuGif(url,imageLoader)
            }
            )

        }


    }

    private fun configuraBotaoSalvar(){
        val  btnSalvar = binding.frmProdutoBtnSalvarActivity

        btnSalvar.setOnClickListener {

            // val produto = produto()
            val dao = ProdutosDao()
            //val produto = CriarProduto()
            val produto = CriarProdutoBinding()
            dao.add(produto)
            Log.i("FrmProduto","onCreate : $produto")
            Log.i("FrmProduto","onCreate : ${dao.buscaTodos()}")

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

        return Produto(nome,desc,valor, imagem = url)
    }


//    fun CriarProduto(): Produto {
//        val campoNome = findViewById<EditText>(R.id.frm_produto_nome_activity)
//        val campodescricao = findViewById<EditText>(R.id.frm_produto_descricao_activity)
//        val campoValor = findViewById<EditText>(R.id.frm_produto_valor_activity)
//
//        val nome = campoNome.text.toString()
//        val desc = campodescricao.text.toString()
//        val valorEmCampo = campoValor.text.toString()
//
//        var valor = if(valorEmCampo.isBlank()){
//
//            BigDecimal.ZERO
//        }else{
//            BigDecimal(valorEmCampo)
//        }
//
//        return Produto(nome,desc,valor)
//    }

}