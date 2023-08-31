package com.example.orgs.ui.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.view.LayoutInflater
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


    fun mostra( xUrl: String? = null,imageLoaderPassada: ImageLoader? = null,quandoImagemCarregado: (
        imagem: String,
        imageLoader: ImageLoader?
    ) -> Unit){
        FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {

            xUrl?.let {
               fmrDialogImagem.tentarCarregarImagemOuGif(it,imageLoaderPassada)

                fmrDialogUrl.setText(it)


            }

            fmrDialogCarregar.setOnClickListener {
                // Pego o valor que está no text
                val url = fmrDialogUrl.text.toString()


                imageLoader = ImageLoader.Builder(context)
                    .componentRegistry {
                        if(Build.VERSION.SDK_INT >= 28){
                            add(ImageDecoderDecoder(context))
                        }else{
                            add(GifDecoder())
                        }

                    }
                    .build()


             fmrDialogImagem.tentarCarregarImagemOuGif(url,imageLoader)


            }
            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar", DialogInterface.OnClickListener { _, _ ->
                    val url = fmrDialogUrl.text.toString()

                       if(imageLoader == null){
                           imageLoader = imageLoaderPassada

                   }
                    quandoImagemCarregado(url,imageLoader)

                })
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener { _, _ ->
                }
                )
                .show()
        }

        }


    }
