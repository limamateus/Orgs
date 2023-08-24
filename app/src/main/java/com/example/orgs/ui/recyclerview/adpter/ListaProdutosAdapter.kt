package com.example.orgs.ui.recyclerview.adpter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Produto


class ListaProdutosAdapter(
    private val context: Context,
    produtos : List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
     val produtos = produtos.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincular(produto: Produto) {
            val nome = itemView.findViewById<TextView>(R.id.produto_item_id)
            val descricao = itemView.findViewById<TextView>(R.id.produto_item_nome)
            val valor = itemView.findViewById<TextView>(R.id.produto_item_valor)

            nome.text = produto.nome
            descricao.text= produto.descricao
            valor.text = produto.valor.toPlainString()


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflater = LayoutInflater.from(context)
       val view = inflater.inflate(R.layout.produto_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val produto = produtos[position]
        holder.vincular(produto)

    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }


}
