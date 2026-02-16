package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Tablero {
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Casilla[][] casillas;

    public Tablero() {
        casillas = new Casilla[FILAS][COLUMNAS];
    }

    public boolean estaVacio() {
        return true;
    }

    private boolean columnaVacia(int columna) {
        return true;
    }
}
