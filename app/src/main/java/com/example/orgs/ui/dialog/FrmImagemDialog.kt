package com.example.orgs.ui.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.extensions.tentarCarregarImagemOuGif

class FrmImagemDialog(
    private val context: Context,


) {
    private  var imageLoader: ImageLoader? = null


    fun mostra(quandoImagemCarregado: (
        imagem: String,
        imageLoader: ImageLoader?
    ) -> Unit){
        val binding= FormularioImagemBinding.inflate(LayoutInflater.from(context))


            binding.fmrDialogCarregar.setOnClickListener {
                // Pego o valor que está no text
                val url = binding.fmrDialogUrl.text.toString()


                imageLoader = ImageLoader.Builder(context)
                    .componentRegistry {
                        if(Build.VERSION.SDK_INT >= 28){
                            add(ImageDecoderDecoder(context))
                        }else{
                            add(GifDecoder())
                        }

                    }
                    .build()
                binding.fmrDialogImagem.tentarCarregarImagemOuGif(url,imageLoader)


            }
            AlertDialog.Builder(context)
                .setView(binding.root)
                .setPositiveButton("Confirmar", DialogInterface.OnClickListener { _, _ ->
                  val url = binding.fmrDialogUrl.text.toString()
                    quandoImagemCarregado(url,imageLoader)

                })
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener { _, _ ->
                }
                )
                .show()
            }
    }
