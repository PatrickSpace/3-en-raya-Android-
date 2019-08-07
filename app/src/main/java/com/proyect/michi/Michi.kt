package com.proyect.michi

public class Michi {

    private var ganador : Int= -1
    var tablero = Array<Array<Int>>(3)
    {
        Array<Int>(3){-1}

    }
    var ultimapos = Array<Int>(2){0}

    fun get_ultimapos():Array<Int>
    {
        return ultimapos
    }
    public fun iniciar_partida()
    {
        for (i in 0..2)
            for(j in 0..2)
                tablero[i][j]=-1

        ganador=-1
    }

    public  fun select_pos(x : Int, y :Int)
    {
        if((x>=0 && x<3) && (y>=0 && y<3) && tablero[x][y]==-1 && ganador==-1)
        {
            tablero[x][y]=0
            ganador=quiengana()
            jugadaCPU()
        }
    }

    public fun quiengana(): Int {
        var aux = -1
        if (tablero[0][0] != -1 && tablero[0][0] == tablero[1][1] && tablero[0][0] == tablero[2][2]) {
            aux = tablero[0][0]
        }
        if (tablero[0][2] != -1 && tablero[0][2] == tablero[1][1] && tablero[0][2] == tablero[2][0]) {
            aux = tablero[0][2]
        }

        for (i in 0..2) {
            if (tablero[i][0] != -1 && tablero[i][0] == tablero[i][1] && tablero[i][0] == tablero[i][2])
                aux = tablero[i][0]

            if (tablero[0][i] != -1 && tablero[0][i] == tablero[1][i] && tablero[0][i] == tablero[2][i])
                aux = tablero[0][i]

        }


        return aux
    }
    public fun Tablero_lleno() : Boolean
        {
            var  tablero_completo : Boolean= true
            for(i in 0..2) {
                for (j in 0..2) {
                    if (tablero[i][j] == -1)
                        tablero_completo = false
                }
            }
            return tablero_completo
        }
    public fun Fin_juego() : Boolean
    {
            var fin = false
            if(Tablero_lleno() || quiengana()!= -1)
                fin = true
            return fin
        }

    public fun jugadaCPU()
    {
        var f = 0
        var c = 0
        var v = -999999999
        var aux = 0

        for (i in 0..2)
        {
             for (j in 0..2)
             {
                 if( tablero[i][j]==-1)
                 {
                     tablero[i][j]=1
                     aux= min()
                     if(aux > v)
                     {
                         v = aux
                         f = i
                         c = j
                     }
                     tablero[i][j] = -1
                 }

             }
        }

        tablero[f][c] = 1
        ultimapos[0]=f
        ultimapos[1]=c
    }

    public fun min():Int
    {
        if (Fin_juego())
        {
            if(quiengana()!=-1)
                return 1
            else
                return  0
        }
        var v = 99999999
        var aux=0
        for (i in 0..2)
        {
            for (j in 0..2)
            {
                if(tablero[i][j]== -1)
                {
                    tablero[i][j] = 0
                    aux = max()
                    if (aux < v)
                        v= aux
                    tablero[i][j] = -1
                }
            }
        }

        return  v
    }

    public fun max():Int
    {
        if (Fin_juego())
        {
            if(quiengana()!=-1)
                return -1
            else
                return  0
        }
        var v = -1000000
        var aux=0
        for (i in 0..2)
        {
            for (j in 0..2)
            {
                if(tablero[i][j]== -1)
                {
                    tablero[i][j] = 1
                    aux = min()
                    if (aux > v)
                        v= aux
                    tablero[i][j] = -1
                }
            }
        }

        return  v
    }
}