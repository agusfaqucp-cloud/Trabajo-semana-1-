  1. Escenario elegido y justificación

El equipo seleccionó la Opción A: Sistema de gestión de turnos para una clínica médica. La decisión surgió del análisis del enunciado y de una votación interna que arrojó consenso unánime por este escenario frente a las otras alternativas disponibles en la consigna.

El problema planteado es concreto, acotado y representativo de una necesidad real: muchas clínicas pequeñas y medianas de Argentina siguen coordinando turnos por teléfono y planillas de Excel, lo que genera errores, superposición de horarios y dificultades de trazabilidad. Digitalizar ese proceso mediante una solución web simple representa un impacto directo y medible en la operación diaria de la clínica.

Desde el punto de vista académico, el escenario obliga al equipo a trabajar con dos perfiles de usuario bien diferenciados —recepcionista y paciente— lo que introduce complejidad de roles, validaciones cruzadas y flujos de negocio que no son triviales. Esto enriquece el aprendizaje porque fuerza a diseñar una arquitectura que separe responsabilidades y contemple permisos distintos por actor.

Además, las funcionalidades mínimas requeridas —solicitar turno, cancelar/reprogramar, ver agenda diaria, notificar al paciente y gestionar estados— configuran un backlog realista y priorizable, ideal para aplicar metodología ágil en un contexto académico con tiempo limitado. El equipo consideró que la amplitud del dominio permite añadir valor incremental sprint a sprint sin necesidad de reescribir la base.

Por último, todos los integrantes del equipo identificaron casos de uso familiares en el escenario (uso propio o de familiares de sistemas de turnos médicos), lo que facilita la validación empática de los flujos de usuario y reduce el riesgo de construir funcionalidades desalineadas con las necesidades reales del actor final.

  2. Metodología de desarrollo: Kanban

El equipo adoptó Kanban como metodología de desarrollo. Si bien la consigna académica menciona Scrum como referencia, el equipo evaluó sus características de trabajo y concluyó que Kanban se adapta mejor al contexto real del grupo durante esta etapa del cuatrimestre.

La principal justificación técnica es la naturaleza del flujo de trabajo: las tareas de este proyecto no tienen una cadencia fija de entrega quincenal ni ceremonias formales que el equipo pueda sostener con regularidad. Kanban permite trabajar en un flujo continuo, donde cada tarea avanza por el tablero según su propio ritmo, sin necesidad de encajar en un sprint predefinido. Esto es especialmente valioso cuando los integrantes tienen cargas académicas variables semana a semana.

Kanban limita el trabajo en progreso (WIP limit) por columna, lo que obliga al equipo a terminar lo que está empezado antes de tomar nuevas tareas. Este principio reduce el contexto cambiante, mejora la concentración individual y evita que el tablero se llene de tarjetas a medias que bloquean el avance real del producto.

La visibilidad del tablero es inmediata: cualquier integrante puede ver en qué está trabajando cada uno, qué está bloqueado y qué quedó pendiente sin necesidad de sincronización verbal diaria. Esto es especialmente útil en equipos distribuidos o con horarios heterogéneos, como es el caso del grupo.

Finalmente, Kanban es una metodología liviana en cuanto a overhead de proceso: no requiere roles formales adicionales (como Product Owner o Scrum Master con dedicación exclusiva), no genera deuda de ceremonias y permite incorporar o reasignar tareas en cualquier momento sin afectar un sprint en curso. Para un proyecto académico de duración limitada, esta flexibilidad es una ventaja competitiva real frente a marcos más rígidos.

  3. Roles del equipo

La asignación de roles se realizó en la primera reunión considerando las habilidades previas de cada integrante, sus preferencias y la necesidad de cobertura equilibrada de las distintas áreas del sistema.


Olivera, Juan Cruz -> Scrum Master	Coordinación general, actas, facilitación de reuniones, seguimiento del tablero Kanban

Gomez Borjas, Agustina Luz -> Dev Lead	Arquitectura técnica, revisión de código, definición de estándares de desarrollo, integración continua

Fritz, Thaiana Ailen	-> QA Lead	Diseño y ejecución de casos de prueba, reporte de bugs, criterios de aceptación, validación de flujos de usuario

Alonso, Angelina Denise	-> UX Lead	Diseño de interfaces, wireframes, prototipado, guía de estilos y experiencia del usuario para ambos perfiles

Gonzalez, Lautaro Sebastian	-> Dev	Desarrollo de funcionalidades back-end y front-end, implementación de endpoints REST, integración con base de datos

Ibarra, Balthazar	-> Dev	Desarrollo de funcionalidades front-end, componentes de interfaz, consumo de API, corrección de issues

  4. Acuerdos de trabajo del equipo

Los acuerdos establecidos a continuación fueron discutidos y aceptados por todos los integrantes en reunión. Su cumplimiento es responsabilidad compartida y el Scrum Master hará seguimiento semanal de su aplicación.

Horarios y disponibilidad
El equipo acordó una reunión de sincronización semanal los días miércoles de 15:00 hs a 16:00 hs, de modalidad virtual vía Discord. Esta reunión no es una daily standup sino una revisión del tablero: se revisa qué avanzó, qué está bloqueado y qué entra en la semana siguiente. Las decisiones técnicas urgentes pueden discutirse de forma asíncrona en un grupo de WhatsApp. Fuera del horario de reunión, cada integrante trabaja según su disponibilidad personal, con el compromiso de mover las tarjetas del tablero dentro de las 24 horas de completar una tarea.

Canales de comunicación
El equipo utiliza tres canales diferenciados según el tipo de comunicación:
•	WhatsApp grupal: comunicación rápida, coordinación de horarios, avisos urgentes y confirmaciones de asistencia a reuniones.
•	Discord (servidor propio): reuniones de sincronización semanales con pantalla compartida, revisión de código en vivo y discusiones técnicas que requieran voz o video.
•	GitHub: toda comunicación relacionada con el código se realiza mediante pull requests y comentarios en commits (GitHub).

Se acordó que las decisiones tomadas en WhatsApp o Discord que afecten al proyecto deben quedar registradas en el tablero kanban como comentario de la tarjeta correspondiente, para garantizar trazabilidad y que ningún integrante quede fuera de contexto.

Frecuencia y convención de commits
El equipo acordó realizar commits pequeños y frecuentes, con un mínimo de un commit por sesión de trabajo, evitando acumular cambios grandes sin versionar.
•	feat: para nuevas funcionalidades (ej: feat: agregar formulario de solicitud de turno)
•	fix: para corrección de bugs (ej: fix: validación de fecha en selector de turnos)
•	chore: para tareas de configuración, dependencias o mantenimiento
•	docs: para cambios en documentación o comentarios de código

Todo código nuevo debe subirse a una rama separada con el prefijo feature/ o fix/ y mergearse a la rama principal (main) mediante un Pull Request con al menos una revisión aprobada de otro integrante. No se permite hacer push directo a main. El Dev Lead (Agustina) es responsable de realizar el merge final y resolver conflictos cuando los haya.


Si una tarjeta permanece más de 48 horas en En revisión sin actividad, el Scrum Master notifica al equipo para desbloquear la situación.

