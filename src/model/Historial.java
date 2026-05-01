package model;

import java.util.ArrayList;
import java.util.List;

public class Historial {

    private static List<Turno> turnos = new ArrayList<>();

    public static void agregarTurno(Turno t) {
        turnos.add(t);
    }

    public static List<Turno> getTurnos() {
        return turnos;
    }

    public static void setTurnos(List<Turno> lista) {
        turnos = lista;
    }

    public static void limpiar() {
        turnos.clear();
    }
}
