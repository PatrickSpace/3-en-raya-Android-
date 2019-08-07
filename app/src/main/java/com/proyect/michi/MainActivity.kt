package com.proyect.michi

import android.support.v7
        .app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {

    val juego = Michi()
    var matriz = Array<Array<Int>>(3)
    {
        Array<Int>(3){-1}
    }
    var ganador= -1

    var multi= Multijugador()
    var isMulti = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        juego.iniciar_partida()
        matriz= juego.tablero

        switch1.setOnClickListener()
        {
            if (switch1.isChecked==true) {
                isMulti = true
                multi.iniciapartida()
                limpiar()
            }

            if(switch1.isChecked==false) {
                isMulti = false
                juego.iniciar_partida()
                limpiar()
            }
        }

        buttonLimpiar.setOnClickListener({
            if (isMulti==false)
                juego.iniciar_partida()
            if (isMulti==true)
                multi.iniciapartida()
            limpiar()

        })
        btn0_0.setOnClickListener({
            evento(0,0,btn0_0)
        })

        btn0_1.setOnClickListener({
            evento(0,1,btn0_1)
        })

        btn0_2.setOnClickListener({
            evento(0,2,btn0_2)
        })

        btn1_0.setOnClickListener({
            evento(1,0,btn1_0)
        })

        btn1_1.setOnClickListener({
            evento(1,1,btn1_1)
        })

        btn1_2.setOnClickListener({
            evento(1,2,btn1_2)
        })

        btn2_0.setOnClickListener({
            evento(2,0,btn2_0)
        })

        btn2_1.setOnClickListener({
            evento(2,1,btn2_1)
        })

        btn2_2.setOnClickListener({
            evento(2,2,btn2_2)
        })

    }

    fun limpiar()
    {
        btn0_0.text=null
        btn0_1.text=null
        btn0_2.text=null
        btn1_0.text=null
        btn1_1.text=null
        btn1_2.text=null
        btn2_0.text=null
        btn2_1.text=null
        btn2_2.text=null
    }

    private fun comprobar ()
    {
        var ultimap = juego.get_ultimapos()

        if(ultimap[0]==0 && ultimap[1]==0)
        {
            btn0_0.text="O"

        }
        if(ultimap[0]==0 && ultimap[1]==1)
        {
            btn0_1.text="O"
        }
        if(ultimap[0]==0 && ultimap[1]==2)
        {
            btn0_2.text="O"
        }
        if(ultimap[0]==1 && ultimap[1]==0)
        {
            btn1_0.text="O"
        }
        if(ultimap[0]==1 && ultimap[1]==1)
        {
            btn1_1.text="O"
        }
        if(ultimap[0]==1 && ultimap[1]==2)
        {
            btn1_2.text="O"
        }
        if(ultimap[0]==2 && ultimap[1]==0)
        {
            btn2_0.text="O"
        }
        if(ultimap[0]==2 && ultimap[1]==1)
        {
            btn2_1.text="O"
        }
        if(ultimap[0]==2 && ultimap[1]==2)
        {
            btn2_2.text="O"
        }

        if(ganador==0)
            Toast.makeText(applicationContext  , "GANASTE",Toast.LENGTH_LONG).show()

        if(ganador==1)
            Toast.makeText(applicationContext  , "PERDISTE",Toast.LENGTH_LONG).show()

        if(ganador== -1 && juego.Fin_juego() )
            Toast.makeText(applicationContext  , "EMPATE",Toast.LENGTH_LONG).show()

    }

    private fun ganar_perder_empatar()
    {
        if(ganador==0)
            Toast.makeText(applicationContext  , "GANA JUGADOR 2",Toast.LENGTH_LONG).show()

        if(ganador==1)
            Toast.makeText(applicationContext  , "GANA JUGADOR 1",Toast.LENGTH_LONG).show()

        if(ganador== -1 && multi.isfin_juego() )
            Toast.makeText(applicationContext  , "EMPATE",Toast.LENGTH_LONG).show()

    }

    private fun evento ( x : Int , y : Int , btn : Button ) {
        if ((juego.Fin_juego()==false) || (multi.isfin_juego())==false)
        {
            if (isMulti == false)
            {
                if (matriz[x][y] == -1)
                {
                    juego.select_pos(x, y)
                    ganador = juego.quiengana()
                    comprobar()
                    btn.text = "X"
                }
            } else
                if (isMulti == true)
                {
                    multi.colocar_pieza(x, y, btn)
                    ganador = multi.quienganapartida()
                    ganar_perder_empatar()
                }

        }
        else
            return
    }



}
