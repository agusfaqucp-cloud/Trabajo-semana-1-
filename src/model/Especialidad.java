package model;

public class Especialidad {
    private int id;
    private final String nombre;
    private final String descripcion;

    public Especialidad(String nombre) {
        this.nombre = nombre;
        this.descripcion = "";
    }

    public Especialidad(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId()             { return id; }
    public String getNombre()      { return nombre; }
    public String getDescripcion() { return descripcion; }
}
