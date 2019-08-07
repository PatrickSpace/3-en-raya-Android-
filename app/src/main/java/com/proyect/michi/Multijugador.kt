package com.proyect.michi

import android.content.Context
import android.widget.Button
import android.widget.Toast

class Multijugador
{

    var turno = 1
    var mtablero = Array<Array<Int>>(3)
    {
        Array<Int>(3){-1}
    }
    var winer=-1

    public fun iniciapartida()
    {
        turno=1
        for (i in 0..2)
            for (j in 0..2)
                mtablero[i][j]=-1

        winer=-1
    }
    public fun istablerolleno() : Boolean
    {
        var  tablero_completo : Boolean= true
        for(i in 0..2) {
            for (j in 0..2) {
                if (mtablero[i][j] == -1)
                    tablero_completo = false
            }
        }
        return tablero_completo
    }

    public fun isfin_juego() : Boolean
    {
        var fin = false
        if(istablerolleno() || quienganapartida()!= -1)
            fin = true
        return fin
    }
    public fun colocar_pieza(x: Int, y:Int, btn : Button)
    {
        if (mtablero[x][y]==-1)
        {
            if (turno==0)
            {
                mtablero[x][y]=0
                btn.text="O"
                winer= quienganapartida()
                turno=1
            }
            else {
                if (turno == 1) {
                    mtablero[x][y] = 1
                    btn.text = "X"
                    winer = quienganapartida()
                    turno = 0
                }
            }
        }
    }

    public fun quienganapartida():Int
    {
        var aux = -1
        if (mtablero[0][0] != -1 && mtablero[0][0] == mtablero[1][1] && mtablero[0][0] == mtablero[2][2]) {
            aux = mtablero[0][0]
        }
        if (mtablero[0][2] != -1 && mtablero[0][2] == mtablero[1][1] && mtablero[0][2] == mtablero[2][0]) {
            aux = mtablero[0][2]
        }

        for (i in 0..2) {
            if (mtablero[i][0] != -1 && mtablero[i][0] == mtablero[i][1] && mtablero[i][0] == mtablero[i][2])
                aux = mtablero[i][0]

            if (mtablero[0][i] != -1 && mtablero[0][i] == mtablero[1][i] && mtablero[0][i] == mtablero[2][i])
                aux = mtablero[0][i]

        }
        return aux
    }



}