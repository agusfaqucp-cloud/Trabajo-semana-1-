package model;

import observer.Observer;
import observer.Subject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Turno implements Subject {

    private final Paciente paciente;
    private final Especialidad especialidad;
    private final Medico medico;
    private final Usuario creadoPor;
    private Estado estado;
    private final String fecha;
    private String motivo;

    private List<Observer> observers = new ArrayList<>();

    public Turno(Paciente paciente, Especialidad especialidad, Medico medico, Usuario usuario) {
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.medico = medico;
        this.creadoPor = usuario;
        this.estado = Estado.PENDIENTE;
        this.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.motivo = "";
    }

    public void cambiarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
        notificar();
    }

    public Paciente     getPaciente()      { return paciente; }
    public Especialidad getEspecialidad()  { return especialidad; }
    public Medico       getMedico()        { return medico; }
    public Usuario      getCreadoPor()     { return creadoPor; }
    public Estado       getEstado()        { return estado; }
    public String       getFecha()         { return fecha; }
    public String       getMotivo()        { return motivo; }
    public void         setMotivo(String m){ this.motivo = m; }

    @Override
    public void agregarObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void eliminarObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificar() {
        for (Observer o : observers) {
            o.actualizar(this);
        }
    }
}
