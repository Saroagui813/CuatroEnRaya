package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Arrays;

public class Tablero {
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Casilla[][] casillas;

    public Tablero() {
        casillas = new Casilla[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    private boolean columnaVacia(int columna) {
        comprobarColumna(columna);
        return (!casillas[0][columna].estaOcupada());
    }

    public boolean estaVacio() {
        boolean vacio = true;
        for (int i = 0; i < COLUMNAS && vacio; i++) {
            vacio = columnaVacia(i);
            }
        return vacio;
    }

    private boolean columnaLlena(int columna) {
        comprobarColumna(columna);
        return (casillas[0][columna].estaOcupada());
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

    public boolean introducirFicha(int columna, Ficha ficha) throws CuatroEnRayaExcepcion {
        comprobarFicha(ficha);
        comprobarColumna(columna);
        if (columnaLlena(columna)) {
            throw new CuatroEnRayaExcepcion("Columna llena.");
        }
        int fila = getPrimeraFilaVacia(columna);
        casillas[fila][columna].setFicha(ficha);
        return comprobarTirada(fila, columna);
    }

    private void comprobarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new NullPointerException("La ficha no puede ser nula.");
        }
    }

    private void comprobarColumna(int columna) {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("Columna incorrecta.");
        }
    }

    private int getPrimeraFilaVacia(int columna) {
        comprobarColumna(columna);
        int filaVacia = 0;
        for (int i = 0; i < FILAS; i++) {
            if (!casillas[i][columna].estaOcupada()) {
                filaVacia = i;
            }
        }
        return filaVacia;
    }

    private boolean comprobarTirada(int fila, int columna) {
        Ficha ficha = casillas[fila][columna].getFicha();
        return comprobarHorizontal(fila, ficha) || comprobarVertical(columna, ficha) || comprobarDiagonalNE(fila, columna, ficha) || comprobarDiagonalNO(fila, columna, ficha);
    }

    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
        return fichasIgualesConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
    }

    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int fichaConsecutivas = 0;
        for (int i = 0; !objetivoAlcanzado(fichaConsecutivas) && i < COLUMNAS; i++) {
            if (casillas[fila][i].estaOcupada() && casillas[fila][i].getFicha().equals(ficha)) {
                fichaConsecutivas++;
            } else {
                fichaConsecutivas = 0;
            }
        }
        return objetivoAlcanzado(fichaConsecutivas);
    }

    private boolean comprobarVertical(int columna, Ficha ficha) {
        int fichaConsecutivas = 0;
        for (int i = 0; !objetivoAlcanzado(fichaConsecutivas) && i < COLUMNAS; i++) {
            if (casillas[i][columna].estaOcupada() && casillas[i][columna].getFicha().equals(ficha)) {
                fichaConsecutivas++;
            } else {
                fichaConsecutivas = 0;
            }
        }
        return objetivoAlcanzado(fichaConsecutivas);
    }

    private boolean comprobarDiagonalNE(int filaActual, int columnaActual, Ficha ficha) {
        int fichaConsecutivas = 0;
        int desplazamiento = menor(filaActual, columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual - desplazamiento;
        for (int i = filaInicial; i < FILAS; i++) {
            for (int j = columnaInicial; j < COLUMNAS; j++) {
                if (casillas[i][j].estaOcupada() && casillas[i][j].getFicha().equals(ficha)) {
                    fichaConsecutivas++;
                }
            }
        }
        return objetivoAlcanzado(fichaConsecutivas);
    }

    private boolean comprobarDiagonalNO(int filaActual, int columnaActual, Ficha ficha) {
        int fichaConsecutivas = 0;
        int desplazamiento = menor(filaActual, COLUMNAS - 1 - columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual + desplazamiento;
        for (int fila = filaInicial, columna = columnaInicial; !objetivoAlcanzado(fichaConsecutivas) && (fila < FILAS && columna >= 0); fila++, columna--) {
            if (casillas[fila][columna].estaOcupada() && casillas[fila][columna].getFicha().equals(ficha)) {
                 fichaConsecutivas++;
            } else {
                fichaConsecutivas = 0;
            }
        }
        return objetivoAlcanzado(fichaConsecutivas);
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

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder("|");
        for (int i = FILAS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNAS; j++) {
                salida.append(casillas[i][j]);
            }
            salida.append(i == 0 ? "|\n" : "|\n|");
        }
        String barraHorizontal = "-".repeat(COLUMNAS);
        salida.append(String.format(" %s%n", barraHorizontal));
        return salida.toString();
    }
}
