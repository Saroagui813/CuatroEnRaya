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

    public boolean estaLleno() {
        return true;
    }

    private boolean columnaLlena(int columna) {
        return true;
    }

    public boolean introducirFicha(int columna, Ficha ficha) {
        return true;
    }

    private void comprobarFicha(Ficha ficha) {

    }

    private void comprobarColumna(int columna) {

    }

    private int getPrimeraFilaVacia(int columna) {
        return columna;
    }

    private boolean comprobarTirada(int fila, int columna) {
        return true;
    }

    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
        return true;
    }

    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        return true;
    }

    private boolean comprobarVertical(int columna, Ficha ficha) {
        return true;
    }

    private boolean comprobarDiagonalNE(int filaActual, int columnaActual, Ficha ficha) {
        return true;
    }

    private boolean comprobarDiagonalNO(int filaActual, int columnaActual, Ficha ficha) {
        return true;
    }

    private int menor(int fila, int columna) {
        return fila;
    }
}
