package model;

public class Paciente {
    private final String nombre;
    private final String dni;
    private final String telefono;
    private final String correo;

    public Paciente(String nombre) {
        this.nombre = nombre;
        this.dni = "";
        this.telefono = "";
        this.correo = "";
    }

    public Paciente(String nombre, String dni, String telefono, String correo) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombre()   { return nombre; }
    public String getDni()      { return dni; }
    public String getTelefono() { return telefono; }
    public String getCorreo()   { return correo; }
}
