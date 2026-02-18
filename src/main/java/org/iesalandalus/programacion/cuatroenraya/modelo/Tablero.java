package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Tablero {
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Casilla[][] casilla;

    public Tablero() {
        casilla = new Casilla[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casilla[i][j] = new Casilla();
            }
        }
    }

    private boolean columnaVacia(int columna) {
        comprobarColumna(columna);
        return (!casilla[0][columna].estaOcupada());
    }

    public boolean estaVacio() {
        boolean vacio = true;
        for (int i = 0; i < COLUMNAS; i++) {
            if (!columnaVacia(i)) {
                vacio = false;
            }
        }
        return vacio;
    }

    private boolean columnaLlena(int columna) {
        comprobarColumna(columna);
        return (casilla[0][columna].estaOcupada());
    }

    public boolean estaLleno() {
        boolean vacio = false;
        for (int i = 0; i < COLUMNAS; i++) {
            if (!columnaVacia(i)) {
                vacio = true;
            }
        }
        return vacio;
    }

    public boolean introducirFicha(int columna, Ficha ficha) {
        return true;
    }

    private void comprobarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new IllegalArgumentException("La ficha no es válida.");
        }
    }

    private void comprobarColumna(int columna) {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("La columna está fuera del rango.");
        }
    }

    private int getPrimeraFilaVacia(int columna) {
        comprobarColumna(columna);
        int filaVacia = 0;
        for (int i = 0; i < FILAS; i++) {
            if (!casilla[i][columna].estaOcupada()) {
                filaVacia = i;
            }
        }
        return filaVacia;
    }

    private boolean comprobarTirada(int fila, int columna) {
        return true;
    }

    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
        return fichasIgualesConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
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
        int menor;
        if (fila > columna) {
            menor = columna;
        } else {
            menor = fila;
        }
        return menor;
    }
}
