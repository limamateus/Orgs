package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.model.Produto
import java.math.BigDecimal

class formulario_produto_activity: AppCompatActivity(R.layout.formulario_produto_activity) {

  //  private val produto = CriarProduto()
    private val dao = ProdutosDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val botaoSalvar = findViewById<Button>(R.id.frm_produto_btn_salvar_activity)

       botaoSalvar.setOnClickListener {

           // val produto = produto()
           //val dao = ProdutosDao()
          val produto = CriarProduto()
           dao.add(produto)
           Log.i("FrmProduto","onCreate : $produto")
           Log.i("FrmProduto","onCreate : ${dao.buscaTodos()}")

           finish()

       }

    }


    fun CriarProduto(): Produto {
        val campoNome = findViewById<EditText>(R.id.frm_produto_nome_activity)
        val campodescricao = findViewById<EditText>(R.id.frm_produto_descricao_activity)
        val campoValor = findViewById<EditText>(R.id.frm_produto_valor_activity)

        val nome = campoNome.text.toString()
        val desc = campodescricao.text.toString()
        val valorEmCampo = campoValor.text.toString()

        var valor = if(valorEmCampo.isBlank()){

            BigDecimal.ZERO
        }else{
            BigDecimal(valorEmCampo)
        }

        return Produto(nome,desc,valor)
    }

}