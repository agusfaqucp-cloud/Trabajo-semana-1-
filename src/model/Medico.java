package model;

public class Medico {
    private final String nombre;
    private final String matriz;
    private final String especialidad;
    private boolean disponibilidad;

    public Medico(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.matriz = "manana";
        this.disponibilidad = true;
    }

    public Medico(String nombre, String matriz, String especialidad, boolean disponibilidad) {
        this.nombre = nombre;
        this.matriz = matriz;
        this.especialidad = especialidad;
        this.disponibilidad = disponibilidad;
    }

    public String getNombre()        { return nombre; }
    public String getMatriz()        { return matriz; }
    public String getEspecialidad()  { return especialidad; }
    public boolean isDisponible()    { return disponibilidad; }
    public void setDisponibilidad(boolean d) { this.disponibilidad = d; }

    @Override
    public String toString() {
        return nombre + " (" + especialidad + ")";
    }
}
