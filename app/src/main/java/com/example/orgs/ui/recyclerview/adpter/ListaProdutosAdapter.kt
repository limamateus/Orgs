package com.example.orgs.ui.recyclerview.adpter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.R
import com.example.orgs.extensions.tentarCarregarImagemOuGif
import com.example.orgs.model.Produto
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


class ListaProdutosAdapter(
    private val context: Context, // Contexto da view
    produtos : List<Produto>, // a lista de produtos
    var quandoClicaNoItem: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
    val produtos = produtos.toMutableList() // nesse momento só passo uma copia da lista para variavel

    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var produto: Produto


        init {
            itemView.setOnClickListener {
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }
        }

        fun vincular(produto: Produto) { // crio um metodo para vincular os dados de um layout no outra
            val nome = binding.produtoItemId // definindo que na variavel nome  é o campo produto_item_id, que no caso era nome
            val descricao = binding.produtoItemNome
            val valor = binding.produtoItemValor
            binding.imageView.tentarCarregarImagemOuGif(produto.imagem.toString())
            nome.text = produto.nome
            descricao.text= produto.descricao
            val valorEmMoeda: String = formatarParaMoedaBrasileira(produto.valor)
            valor.text = valorEmMoeda



//            if(produto.imagem != null || produto.imagem != ""){
//                if(produto.imageLoader == null){
//                    image.load(produto.imagem){
//                        fallback(R.drawable.erro)
//                        error(R.drawable.erro)
//
//                    }
//                }else{
//                    image.load(produto.imagem,produto.imageLoader){
//                        fallback(R.drawable.erro)
//                        error(R.drawable.erro)
//                    }
//                }
//
//            }


            //        fun vincular(produto: Produto) { // crio um metodo para vincular os dados de um layout no outra
//            val nome = itemView.findViewById<TextView>(R.id.produto_item_id) // definindo que na variavel nome  é o campo produto_item_id, que no caso era nome
//            val descricao = itemView.findViewById<TextView>(R.id.produto_item_nome)
//            val valor = itemView.findViewById<TextView>(R.id.produto_item_valor)
//
//            nome.text = produto.nome
//            descricao.text= produto.descricao
//            valor.text = produto.valor.toPlainString()


        }

        private fun formatarParaMoedaBrasileira(valor: BigDecimal): String {
            val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val valorEmMoeda: String = numberFormat.format(valor)
            return valorEmMoeda
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflater = LayoutInflater.from(context) // crio uma infrator onde irá pegar o contexto passado no paramentro
       val binding = ProdutoItemBinding.inflate(inflater,parent,false) // crio uma view
        return ViewHolder(binding) // retorno para ViewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val produto = produtos[position] // aqui é a posição que será passadao
        holder.vincular(produto)

    }

    override fun getItemCount(): Int = produtos.size // quantidade de itens

    fun atualiza(produtos: List<Produto>) { // essa é uma funçao para atualizar o aptard
        this.produtos.clear() // eu limpo o adpter
        this.produtos.addAll(produtos) // adiciono os produtos
        notifyDataSetChanged() // notifico
    }


}
