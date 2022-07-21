package com.diazeduardo.memorama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var listaImageViewRotas=ArrayList<ImageView>()
    var listaImageView=ArrayList<ImageView>()

    var listaPosiciones = ArrayList<Int>()
    var listaClasesImagen=ArrayList<Imagen>()

    var imagen1 = Imagen()
    var imagen2 = Imagen()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaImageView.add(img0)
        listaImageView.add(img1)
        listaImageView.add(img2)
        listaImageView.add(img3)
        listaImageView.add(img4)
        listaImageView.add(img5)
        listaImageView.add(img6)
        listaImageView.add(img7)

        listaImageViewRotas.add(img0Rota)
        listaImageViewRotas.add(img1Rota)
        listaImageViewRotas.add(img2Rota)
        listaImageViewRotas.add(img3Rota)
        listaImageViewRotas.add(img4Rota)
        listaImageViewRotas.add(img5Rota)
        listaImageViewRotas.add(img6Rota)
        listaImageViewRotas.add(img7Rota)

        ocultarImagenes()
        cargarPosicionesRandom()
     //   mostrarImagen()
    }

    //Mostrar inicio Juego

    fun ocultarImagenes() {
        for (i in 0..7){
            listaImageViewRotas[i].visibility=View.VISIBLE
            listaImageView[i].visibility=View.INVISIBLE
        }
    }
    fun mostrarImagen() {
        for (i in 0..7){
            listaImageViewRotas[i].visibility=View.INVISIBLE
            listaImageView[i].visibility=View.VISIBLE
        }
    }

    fun cargarPosicionesRandom(){
        listaPosiciones.add(0)
        listaPosiciones.add(1)
        listaPosiciones.add(2)
        listaPosiciones.add(3)
        listaPosiciones.add(4)
        listaPosiciones.add(5)
        listaPosiciones.add(6)
        listaPosiciones.add(7)
//Mostrando arreglo ordenado
        Log.d("Mensaje","original $listaPosiciones")

        //Desordenar arreglo
        Collections.shuffle(listaPosiciones)
        //Arreglo desordenado
        Log.d("Mensaje","original $listaPosiciones")

    listaClasesImagen.add(Imagen())
    listaClasesImagen.add(Imagen())
    listaClasesImagen.add(Imagen())
    listaClasesImagen.add(Imagen())
    listaClasesImagen.add(Imagen())
    listaClasesImagen.add(Imagen())
    listaClasesImagen.add(Imagen())
    listaClasesImagen.add(Imagen())
//Cada imagen debe tener dos posiciones random

        for (i in 0..7){
            var posicion = listaPosiciones[i]
            var id_drawable= 0

            when(i){
                0,1-> id_drawable=R.drawable.ic_plane
                2,3->id_drawable=R.drawable.ic_taza
                4,5->id_drawable=R.drawable.ic_feliz
                6,7->id_drawable=R.drawable.ic_laptop
            }

            //Agregar imagen a imageView
            listaImageView[posicion].setImageResource(id_drawable)
            var objetImagen = Imagen().apply {
                this.id_vector =id_drawable
            }
//Ponemos cada imagen en su posicion

            listaClasesImagen[posicion]= objetImagen

        }
    }

    fun procesarImagen(imagenView:View){
        var posiciomSeleccionada = imagenView.tag.toString().toInt()
        var id = imagenView.resources.getResourceEntryName(imagenView.id)

        if (imagenView is ImageView){
            when(id){
                "img0Rota"->{
                    mostrarOcultarImagen(posiciomSeleccionada,true)
                }
                "img1Rota"->{
                    mostrarOcultarImagen(posiciomSeleccionada,true)
                }
                "img2Rota"->{
                    mostrarOcultarImagen(posiciomSeleccionada,true)
                }
                "img3Rota"->{
                    mostrarOcultarImagen(posiciomSeleccionada,true)
                }
                "img4Rota"->{
                    mostrarOcultarImagen(posiciomSeleccionada,true)
                }
                "img5Rota"->{
                    mostrarOcultarImagen(posiciomSeleccionada,true)
                }
                "img6Rota"->{
                    mostrarOcultarImagen(posiciomSeleccionada,true)
                }
                "img7Rota"->{
                    mostrarOcultarImagen(posiciomSeleccionada,true)
                }
                "img0"->{
                    mostrarOcultarImagen(posiciomSeleccionada,false)
                }
                "img1"->{
                    mostrarOcultarImagen(posiciomSeleccionada,false)
                }
                "img2"->{
                    mostrarOcultarImagen(posiciomSeleccionada,false)
                }
                "img3"->{
                    mostrarOcultarImagen(posiciomSeleccionada,false)
                }
                "img4"->{
                    mostrarOcultarImagen(posiciomSeleccionada,false)
                }
                "img5"->{
                    mostrarOcultarImagen(posiciomSeleccionada,false)
                }
                "img6"->{
                    mostrarOcultarImagen(posiciomSeleccionada,false)
                }
                "img7"->{
                    mostrarOcultarImagen(posiciomSeleccionada,false)
                }
                }
            }

        asignarImagen(posiciomSeleccionada)
        }


 fun mostrarOcultarImagen(posicion: Int, mostrar: Boolean) {
if (mostrar){
    listaImageView[posicion].visibility=View.VISIBLE
    listaImageViewRotas[posicion].visibility=View.INVISIBLE
}else{
    listaImageView[posicion].visibility=View.INVISIBLE
    listaImageViewRotas[posicion].visibility=View.VISIBLE
}
    }

    fun asignarImagen(posicion: Int){
        if (imagen1.asignada){
            imagen2=listaClasesImagen[posicion]
            imagen2.posicion=posicion
            imagen2.asignada=true

            if (imagen1.id_vector==imagen2.id_vector){
                mostrarMensaje("Ah perro")
            }else
            {
                mostrarMensaje("Ya la cagaste")
                Handler().postDelayed({
                    mostrarOcultarImagen(imagen1.posicion,false)
                    mostrarOcultarImagen(imagen2.posicion,false)
                },200)
            }
            imagen1.asignada = false
            imagen2.asignada=false
        }else{
            imagen1= listaClasesImagen[posicion]
            imagen1.asignada=true
            imagen1.posicion=posicion
        }
    }
    fun mostrarMensaje(msg:String){
        Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
    }

    fun reiniciarJuego(view: View) {
      var intent = getIntent()
        finish()
        startActivity(intent)
    }
}