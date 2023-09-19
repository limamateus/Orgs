package com.example.orgs.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.databinding.ActivityLoginBinding
import com.example.orgs.extensions.vaiPara

class LoginActivity: AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        configurarBotaoCadastrar()
        configuraBotaoEntrar()
    }

    private fun configuraBotaoEntrar() {
       binding.activityLoginBotaoEntrar.setOnClickListener {
           val usuario = binding.activityLoginUsuario.text.toString()
           val senha = binding.activityLoginSenha.text.toString()

           vaiPara(ListaProdutosActivity::class.java)

       }
    }

    private fun configurarBotaoCadastrar() {
       binding.activityLoginBotaoCadastrar.setOnClickListener {
           vaiPara(FormularioCadastroUsuarioActivity::class.java)
       }
    }
}