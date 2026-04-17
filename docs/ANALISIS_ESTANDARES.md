# Análisis de Estándares — MediTurnos

**Proyecto:** Sistema de gestión de turnos médicos  
**TP:** Trabajo Práctico N°2 — Estándares en validación de sistemas  
**Patrones implementados (TP1):** Strategy + Observer (GoF)

---

## Tabla comparativa de estándares

| Estándar | Año (aprox.) | Enfoque principal | ¿Aplica a nuestro proyecto? | Justificación |
|---|---|---|---|---|
| **ISO 9241-11** | 1998 (rev. 2018) | Usabilidad: eficacia, eficiencia y satisfacción del usuario en un contexto de uso definido | Sí, directamente | El sistema tiene interfaz gráfica con múltiples tipos de usuario (admin, médico, paciente). Cada rol tiene distintos niveles de experiencia técnica y distintas necesidades operativas. La separación visual por rol y la paleta de colores por estado del turno son decisiones que responden directamente a este estándar. |
| **ISO 13407** | 1999 (reemplazada por ISO 9241-210 en 2010) | Proceso de diseño centrado en el humano: cuatro fases iterativas con participación activa del usuario | Parcialmente | Las primeras tres fases (contexto de uso, requisitos del usuario, soluciones de diseño) se aplicaron de forma implícita durante el desarrollo del TP1. La fase cuatro (evaluación con usuarios reales) no se realizó formalmente. En un proyecto real esto sería un incumplimiento crítico. |
| **ISO/IEC 27001** | 2005 (rev. 2022) | Sistema de Gestión de Seguridad de la Información: confidencialidad, integridad y disponibilidad de datos | Sí, es el más urgente | El sistema maneja datos personales de pacientes (nombre, especialidad médica, historial de turnos). En Argentina estos datos son considerados sensibles por la Ley 25.326. El prototipo actual tiene autenticación básica con roles pero carece de cifrado, registro de auditoría y política de contraseñas robusta. |
| **ISA/IEC 62443** | 2007 (familia en desarrollo continuo) | Ciberseguridad en sistemas de automatización y control industrial (SCADA, PLC, infraestructura crítica) | No en el estado actual | MediTurnos no controla infraestructura física ni dispositivos industriales. Sin embargo, si el sistema se integrara a equipos de monitoreo de pacientes o a infraestructura hospitalaria conectada (IoT médico), esta norma pasaría a aplicar de forma directa e inmediata. |
| **ISO 9001** | 1987 (rev. 2015) | Gestión de calidad en procesos: diseño, verificación, validación y control de cambios | Sí, al proceso de desarrollo | La norma aplica al proceso con el que desarrollamos el sistema, no solo al producto. Exige trazabilidad entre requisitos y código, criterios de aceptación definidos antes de las pruebas, y registros de revisión de diseño. El diagrama de clases, la justificación de patrones y esta documentación son evidencia parcial de cumplimiento. |

---

## Conclusión

Si tuviéramos que certificar MediTurnos bajo un estándar actual, elegiríamos **ISO/IEC 27001** como punto de partida, principalmente porque el sistema opera en el ámbito de la salud y maneja datos personales sensibles. Obtener esta certificación implicaría cambios concretos en el diseño actual: habría que agregar una capa de cifrado sobre la persistencia en JSON (actualmente los datos se guardan en texto plano), implementar un registro de auditoría que capture quién hizo qué y cuándo (lo que reforzaría el patrón Observer ya existente, redirigiendo sus notificaciones también a un log persistente firmado), y endurecer el módulo de autenticación reemplazando las credenciales hardcodeadas por un sistema con hash de contraseñas y bloqueo por intentos fallidos. A nivel arquitectural, el patrón Observer facilita el cumplimiento porque ya existe un mecanismo de notificación desacoplado que podría extenderse para registrar eventos de seguridad sin modificar las clases de dominio. El patrón Strategy, por su parte, permitiría intercambiar la estrategia de autenticación sin tocar el resto del sistema, lo cual es exactamente el tipo de flexibilidad que una auditoría de seguridad valora al evaluar la mantenibilidad del SGSI a lo largo del tiempo.

---

## Relación entre decisiones de diseño del TP1 y los estándares

### Patrón Observer — favorece ISO/IEC 27001 e ISO 9001

El patrón Observer implementado en `ServicioNotificacion` ya proporciona un mecanismo de notificación desacoplado que reacciona ante cambios de estado en los turnos. Desde la perspectiva de ISO/IEC 27001, este patrón es una base natural para implementar un sistema de auditoría: cada vez que un turno cambia de estado, el observer podría registrar el evento en un log persistente con timestamp, usuario responsable y estado anterior/nuevo. Esto cubre directamente el control A.8.15 (Logging) del Anexo A de la norma. Desde ISO 9001, el mismo mecanismo permite generar trazabilidad de operaciones sin necesidad de modificar las clases de dominio, lo cual facilita las auditorías de proceso.

### Patrón Strategy — favorece ISO 9001 e ISO 9241-11

La interfaz `EstrategiaAsignacion` y su implementación `AsignacionPorDisponibilidad` permiten cambiar el algoritmo de asignación de turnos sin modificar el contexto (`GestorTurnos`). Desde ISO 9001 esto es positivo porque los cambios de comportamiento están localizados y son verificables de forma aislada, lo que simplifica el proceso de validación cuando se modifica una estrategia. Desde ISO 9241-11 también hay un beneficio indirecto: si en el futuro se identificara que una estrategia de asignación particular genera confusión en los usuarios (por ejemplo, asignando médicos de especialidades que no coinciden con lo solicitado), se podría reemplazar sin impacto en la interfaz.

### Sistema de roles (`Rol.java`) — favorece ISO/IEC 27001

La incorporación del enum `Rol` y la diferenciación de permisos por tipo de usuario (admin, médico, paciente) en `VentanaPrincipal` es una implementación básica del principio de **privilegio mínimo**, que es uno de los conceptos centrales de ISO/IEC 27001. Cada usuario solo puede realizar las operaciones que corresponden a su rol. La limitación actual es que este control existe solo en la capa de presentación: no hay validación en la capa de negocio ni en la persistencia. Una auditoría de seguridad observaría esto como un control superficial que podría bypassearse con acceso directo a los datos o manipulando el objeto de sesión.

### Persistencia en texto plano — dificulta ISO/IEC 27001

El módulo `PersistenciaTurnos` guarda los datos en archivos JSON y TXT sin ningún tipo de cifrado ni control de integridad. Desde ISO/IEC 27001 esto es un riesgo directo sobre la confidencialidad e integridad de los datos. Cualquier persona con acceso al sistema de archivos puede leer o modificar los turnos guardados sin dejar rastro. Para cumplir con el estándar habría que cifrar el archivo antes de escribirlo (al menos con una clave derivada de las credenciales del sistema) y agregar un hash o firma digital que permita detectar modificaciones no autorizadas.
