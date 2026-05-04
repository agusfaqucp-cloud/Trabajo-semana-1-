# Registro de uso de IA - MediTurnos

**Proyecto:** MediTurnos - Sistema de gestion de turnos medicos  
**Materia:** Ingenieria de Software II

---

## TP2 - Pruebas de Software y Diseño de Interfaz

### Herramientas utilizadas

- **Claude (Anthropic)** - Asistente de IA utilizado para consultas puntuales durante el desarrollo del TP2

---

### Detalle de uso

#### Diseño de interfaz (Parte A)

Usamos IA para consultar como estructurar el analisis de usuario segun lo que pide la norma ISO 9241-11 y como relacionarlo con ISO 13407. Teniamos claro quienes eran los usuarios del sistema (admin, medico, paciente) pero la IA nos ayudo a organizar esa informacion en el formato que pide la consigna y a entender que metricas usar para la auditoria de usabilidad.

Las capturas del prototipo Figma, la navegacion entre pantallas y la decision de que pantallas incluir fueron definidas y realizadas por el equipo. La IA no intervino en el disenio visual del sistema que ya estaba hecho desde el TP1.

**Modificaciones del equipo:** todo el contenido fue revisado y adaptado al sistema real. Los criterios de eficiencia y satisfaccion elegidos para la auditoria surgen de problemas que identificamos nosotros al usar el sistema, la IA solo ayudo a formalizarlos con el vocabulario de la norma.

---

#### Pruebas unitarias (Parte B)

Consultamos a la IA sobre la diferencia entre clases de equivalencia y valores limite, y como aplicarlas a nuestras clases especificas. Teniamos entendimiento basico de testing pero no habiamos aplicado estas tecnicas formalmente antes.

La IA propuso una estructura inicial para los casos de prueba aplicados a `GestorTurnos` y `PersistenciaTurnos`. El equipo reviso cada caso, verifico que los metodos existieran en el codigo real, corrio los tests localmente y ajusto los que no funcionaban.

Para la configuracion del `pom.xml` y el `test.yml` de GitHub Actions, la IA explico como funcionan estas herramientas y genero los archivos base. El equipo tuvo que modificar el `pom.xml` varias veces hasta que el pipeline corrio correctamente, incluyendo la decision de excluir las clases de Swing que no compilan en el servidor de Actions.

**Modificaciones del equipo:** los tests fueron ejecutados y verificados por el equipo en VS Code. La estructura de carpetas (`pruebas/unit/`), la subida del codigo al repositorio y la resolucion de errores de compilacion en Actions fueron realizadas por el equipo de forma independiente.

---

#### Funcionalidades nuevas agregadas en TP2

Se agrego cierre automatico de sesion por inactividad en `VentanaPrincipal.java`. La IA genero el codigo base usando `Timer` de Java y `AWTEventListener` para detectar actividad del usuario. El equipo reviso que el comportamiento fuera correcto, que el contador se mostrara en la barra de estado y que el sistema volviera al login correctamente al expirar la sesion.

Esta funcionalidad se agrego como respuesta a una observacion del profesor sobre seguridad del sistema, y esta directamente relacionada con el principio de menor exposicion de datos de ISO/IEC 27001 que analizamos en el TP anterior.

---

#### Lo que NO hizo la IA

- No disenio ninguna pantalla del sistema
- No definio que patrones de disenio usar (eso fue del TP1)
- No subio ningun archivo al repositorio
- No configuro GitHub en la computadora del equipo
- No resolvio los errores de compilacion de Actions (eso lo resolvio el equipo probando)
- No grabo el video de evidencia de los tests

---

### Justificacion del uso

Usamos IA como herramienta de apoyo para entender conceptos nuevos (testing formal, configuracion de Maven, GitHub Actions) y para agilizar la generacion de documentacion estructurada. En todos los casos el equipo reviso, modifico y valido lo generado antes de incluirlo en el trabajo. El uso de IA fue transparente y acotado a tareas de soporte, no de reemplazo del trabajo del equipo.

---

*Documento actualizado en el marco del TP2 - Ingenieria de Software II*
