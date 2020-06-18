package com.bancolombia.screenplay.avianca.model.busqueda;

public enum FechaDeSalida {

    manana(1);

    private final int diaDesdeHoy;

    FechaDeSalida(int diaDesdeHoy) {
        this.diaDesdeHoy = diaDesdeHoy;
    }

    public int diaDesdeHoy() {
        return diaDesdeHoy;
    }
}
