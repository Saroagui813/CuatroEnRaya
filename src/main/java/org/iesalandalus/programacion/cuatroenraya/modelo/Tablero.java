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

    public boolean introducirFicha(int columna, Ficha ficha) throws CuatroEnRayaExcepcion {
        comprobarFicha(ficha);
        comprobarColumna(columna);
        if (columnaLlena(columna)) {
            throw new CuatroEnRayaExcepcion("Columna llena.");
        }
        int fila = getPrimeraFilaVacia(columna);
        casilla[fila][columna].setFicha(ficha);
        return comprobarTirada(fila, columna);
    }

    private void comprobarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new NullPointerException("La ficha no puede ser nula.");
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
        int fichaConsecutivas = 0;
        for (int i = 0; !objetivoAlcanzado(fichaConsecutivas) && i < COLUMNAS; i++) {
            if (casilla[fila][i].estaOcupada() && casilla[fila][i].getFicha().equals(ficha)) {
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
            if (casilla[i][columna].estaOcupada() && casilla[i][columna].getFicha().equals(ficha)) {
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
                if (casilla[i][j].estaOcupada() && casilla[i][j].getFicha().equals(ficha)) {
                    fichaConsecutivas++;
                }
            }
        }
        return objetivoAlcanzado(fichaConsecutivas);
    }

    private boolean comprobarDiagonalNO(int filaActual, int columnaActual, Ficha ficha) {
        int fichaConsecutivas = 0;
        int desplazamiento = menor(filaActual, columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual - desplazamiento;
        for (int i = filaInicial; i < 0; i++) {
            for (int j = columnaInicial; j < COLUMNAS; j++) {
                if (casilla[i][j].estaOcupada() && casilla[i][j].getFicha().equals(ficha)) {
                    fichaConsecutivas++;
                }
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
}
