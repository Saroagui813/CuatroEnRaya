package org.iesalandalus.programacion.cuatroenraya.modelo;

import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

    private Consola(){

    }

    public static String leerNombre() {

    }

    public static Ficha elegirColorFicha() {
        int eleccion;
        do {
            System.out.println("Elige el color de tus fichas (0-AZUL, 1-VERDE): ");
            eleccion = Entrada.entero();
        } while (eleccion < 0 || eleccion > 1);
        return Ficha.values()[eleccion];
    }

    public static Jugador leerJugador() {
        System.out.println("Introduce los datos del PRIMER jugador");
        return new Jugador(leerNombre(), elegirColorFicha());
    }
}
