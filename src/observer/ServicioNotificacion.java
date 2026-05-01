package observer;

import model.Turno;
import java.util.ArrayList;
import java.util.List;

public class ServicioNotificacion implements Observer {

    private final List<String> historialNotificaciones = new ArrayList<>();
    private final List<Runnable> uiListeners = new ArrayList<>();

    @Override
    public void actualizar(Turno turno) {
        String mensaje = "Turno de " + turno.getPaciente().getNombre()
                + " - estado: " + turno.getEstado()
                + " (" + turno.getMedico().getNombre() + ")";

        historialNotificaciones.add(mensaje);
        System.out.println("Notificacion: " + mensaje);

        for (Runnable listener : uiListeners) {
            listener.run();
        }
    }

    public List<String> getHistorial() {
        return historialNotificaciones;
    }

    public String getUltimaNotificacion() {
        if (historialNotificaciones.isEmpty()) return "";
        return historialNotificaciones.get(historialNotificaciones.size() - 1);
    }

    public void agregarUIListener(Runnable listener) {
        uiListeners.add(listener);
    }
}
