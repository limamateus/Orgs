package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.orgs.databinding.ActivityViewProdutoDadosBinding
import com.example.orgs.model.Produto

class Activity_View_Produto_Dados : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            val produto = intent.getSerializableExtra("produto") as Produto
        title ="detalhes do produto"
        ActivityViewProdutoDadosBinding.inflate(layoutInflater).apply {

            setContentView(root)

            activityViewProdutoDadosImagemView.load(produto.imagem)
            activityViewProdutoDadosTitulo.text = produto.nome
            activityViewProdutoDadosTexto.text = produto.descricao
            activityViewProdutoDadosValor.text =  produto.valor.toPlainString()



        }



    }

}