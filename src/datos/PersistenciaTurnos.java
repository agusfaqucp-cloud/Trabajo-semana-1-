package datos;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTurnos {

    private static final String ARCHIVO_JSON = "turnos.json";
    private static final String ARCHIVO_TXT  = "turnos.txt";

    public static void guardarJSON(List<Turno> turnos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_JSON))) {
            pw.println("[");
            for (int i = 0; i < turnos.size(); i++) {
                Turno t = turnos.get(i);
                pw.println("  {");
                pw.println("    \"paciente\": \"" + escapar(t.getPaciente().getNombre()) + "\",");
                pw.println("    \"especialidad\": \"" + escapar(t.getEspecialidad().getNombre()) + "\",");
                pw.println("    \"medico\": \"" + escapar(t.getMedico().getNombre()) + "\",");
                pw.println("    \"estado\": \"" + t.getEstado().toString() + "\",");
                pw.println("    \"fecha\": \"" + escapar(t.getFecha()) + "\",");
                pw.println("    \"creadoPor\": \"" + (t.getCreadoPor() != null ? escapar(t.getCreadoPor().getNombre()) : "") + "\"");
                pw.print("  }");
                if (i < turnos.size() - 1) pw.print(",");
                pw.println();
            }
            pw.println("]");
        } catch (IOException e) {
            System.err.println("Error guardando JSON: " + e.getMessage());
        }
    }

    public static void guardarTXT(List<Turno> turnos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_TXT))) {
            pw.println("=== MediTurnos - Registro de turnos ===");
            pw.println();
            for (int i = 0; i < turnos.size(); i++) {
                Turno t = turnos.get(i);
                pw.println("Turno #" + (i + 1));
                pw.println("  Paciente    : " + t.getPaciente().getNombre());
                pw.println("  Especialidad: " + t.getEspecialidad().getNombre());
                pw.println("  Medico      : " + t.getMedico().getNombre());
                pw.println("  Estado      : " + t.getEstado().toString());
                pw.println("  Fecha       : " + t.getFecha());
                pw.println("  Creado por  : " + (t.getCreadoPor() != null ? t.getCreadoPor().getNombre() : "-"));
                pw.println();
            }
        } catch (IOException e) {
            System.err.println("Error guardando TXT: " + e.getMessage());
        }
    }

    public static List<Turno> cargarJSON() {
        List<Turno> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_JSON);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            String paciente = null, especialidad = null, medico = null;
            String estado = null, fecha = null, creadoPor = null;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.startsWith("\"paciente\""))    paciente    = extraerValor(linea);
                if (linea.startsWith("\"especialidad\"")) especialidad = extraerValor(linea);
                if (linea.startsWith("\"medico\""))      medico      = extraerValor(linea);
                if (linea.startsWith("\"estado\""))      estado      = extraerValor(linea);
                if (linea.startsWith("\"fecha\""))       fecha       = extraerValor(linea);
                if (linea.startsWith("\"creadoPor\""))   creadoPor   = extraerValor(linea);

                if (linea.startsWith("}") && paciente != null) {
                    Paciente p = new Paciente(paciente);
                    Especialidad esp = new Especialidad(especialidad != null ? especialidad : "");
                    Medico m = new Medico(medico != null ? medico : "Sin asignar", "");
                    Usuario u = creadoPor != null && !creadoPor.isEmpty()
                            ? new Usuario(creadoPor, Rol.ADMIN) : null;

                    Turno t = new Turno(p, esp, m, u);
                    if (estado != null) {
                        try {
                            t.cambiarEstado(Estado.valueOf(estado));
                        } catch (IllegalArgumentException ignored) {}
                    }
                    lista.add(t);

                    paciente = null; especialidad = null; medico = null;
                    estado = null; fecha = null; creadoPor = null;
                }
            }
        } catch (IOException e) {
            System.err.println("Error cargando JSON: " + e.getMessage());
        }

        return lista;
    }

    private static String extraerValor(String linea) {
        int inicio = linea.indexOf('"', linea.indexOf(':') + 1) + 1;
        int fin    = linea.lastIndexOf('"');
        if (inicio < 1 || fin < inicio) return "";
        return linea.substring(inicio, fin);
    }

    private static String escapar(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
