package com.example.orgs.ui.recyclerview.adpter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.extensions.formataParaMoedaBrasileira
import com.example.orgs.extensions.tentarCarregarImagemOuGif
import com.example.orgs.model.Produto


class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto> = emptyList(),
    // declaração da função para o listener do adapter
    var quandoClicaNoItem: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    // utilização do inner na classe interna para acessar membros da classe superior
    // nesse caso, a utilização da variável quandoClicaNoItem
    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Considerando que o ViewHolder modifica de valor com base na posição
        // é necessário o uso de properties mutáveis, para evitar nullables
        // utilizamos o lateinit, properties que podem ser inicializar depois
        private lateinit var produto: Produto

        init {
            // implementação do listener do adapter
            itemView.setOnClickListener {
                // verificação da existência de valores em property lateinit
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }
        }

        fun vincula(produto: Produto) {
            this.produto = produto
            val nome = binding.produtoItemId
            nome.text = produto.nome
            val descricao = binding.produtoItemNome
            descricao.text = produto.descricao
            val valor = binding.produtoItemValor
            val valorEmMoeda: String = produto.valor
                .formataParaMoedaBrasileira()
            valor.text = valorEmMoeda

            binding.imageView.tentarCarregarImagemOuGif(produto.imagem)


        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflater = LayoutInflater.from(context) // crio uma infrator onde irá pegar o contexto passado no paramentro
       val binding = ProdutoItemBinding.inflate(inflater,parent,false) // crio uma view
        return ViewHolder(binding) // retorno para ViewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val produto = produtos[position] // aqui é a posição que será passadao
        holder.vincula(produto)

    }

    override fun getItemCount(): Int = produtos.size // quantidade de itens

    fun atualiza(produtos: List<Produto>) { // essa é uma funçao para atualizar o aptard
        this.produtos.clear() // eu limpo o adpter
        this.produtos.addAll(produtos) // adiciono os produtos
        notifyDataSetChanged() // notifico
    }


}
