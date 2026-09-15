# Post-contenido — Unidad 4: Patrones de Comportamiento en ComprasUDES

## David Santiago Rincon Bautista - 1152327

## Link del repo: https://github.com/DavidRincon12/rincon-post1-u4

## Descripción
Repositorio del post-contenido de la Unidad 4 de Patrones de Diseño de Software.
Un único proyecto Spring Boot (compras-comportamiento) que resuelve cuatro necesidades
reales del backend de ComprasUDES, el sistema interno de solicitudes de compra
corporativas: aprobación por niveles jerárquicos, ejecución reversible de solicitudes
aprobadas, notificaciones ante cambios de estado y reglas de transición según el
estado actual de la solicitud.

## Cómo ejecutar
```
mvn clean package
mvn spring-boot:run
mvn test
```

## Decisiones de diseño

### Necesidad 1 — Aprobación por niveles jerárquicos
Se aplicó el patrón **Cadena de Responsabilidad**. Cada nivel de aprobación
(RevisorCumplimiento → Supervisor → Gerente → Director) es un eslabón independiente
que decide si puede resolver la solicitud según el monto o la categoría, o la delega
al siguiente. Agregar o reordenar un nivel solo requiere modificar la construcción de
la cadena en `CadenaAprobacionService`, sin tocar `ControladorSolicitudes` ni los
demás niveles. La alternativa más cercana descartada fue el patrón **Comando**: aunque
también encapsula comportamiento, un Comando modela una acción reversible que un
invocador almacena y ejecuta deliberadamente; no tiene lógica de guardia para decidir
si delega al siguiente handler, ni estructura de cadena ordenada. Usar Comando aquí
obligaría a codificar la secuencia de niveles en el invocador, violando la restricción
de que el código que dispara la evaluación no debe saber cuántos niveles existen.

### Necesidad 2 — Ejecución reversible de solicitudes
Se aplicó el patrón **Comando**. Reservar presupuesto y generar orden de compra se
encapsulan como objetos `Comando` con métodos `ejecutar()` y `deshacer()`. El
`EjecutorSolicitud` mantiene un historial ordenado (`Deque<Comando>`) que permite
consultar todas las operaciones realizadas y deshacer únicamente la última, sin afectar
las anteriores. La alternativa descartada fue **Cadena de Responsabilidad**: aquí no
existe ningún decisor evaluando condiciones para decidir si delega una petición
entrante; hay operaciones discretas que un actor ejecuta y puede revertir, y que deben
quedar registradas en orden. La Cadena no tiene mecanismo de historial ni de undo, y
modelarla como cadena implicaría que cada servicio decidiría si "pasa" la operación al
siguiente, lo cual no corresponde al problema real.

### Necesidad 3 — Notificaciones ante cambio de estado
Se aplicó el patrón **Observador**. El `NotificadorEstado` actúa como sujeto y
mantiene una lista de `ObservadorEstado`. Cuando el estado de una solicitud cambia,
el notificador recorre la lista e invoca `alCambiarEstado()` en cada observador
(correo, dashboard, auditoría). Agregar una cuarta reacción solo requiere registrar
un nuevo observador con `mecanismo.registrar(...)`, sin modificar `NotificadorEstado`
ni el código que cambia el estado. La alternativa descartada fue el patrón **Estado**
(Necesidad 4): ese patrón controla qué operaciones son válidas sobre la solicitud
según su estado interno; no es adecuado para que módulos externos e independientes
reaccionen a un cambio ya ocurrido. Usar Estado aquí requeriría que la propia
solicitud conociera los módulos de correo, dashboard y auditoría, acoplando la
entidad de dominio con infraestructura de notificación.

### Necesidad 4 — Reglas de transición según el estado
Se aplicó el patrón **Estado**. Cada estado posible de la solicitud (Pendiente,
Aprobada, Rechazada, Ejecutada, Cancelada) se representa como una clase que implementa
`EstadoSolicitud`. El `ContextoSolicitud` deriva el estado actual de la solicitud y
delega cada operación (aprobar, rechazar, ejecutar, cancelar) al objeto de estado
correspondiente, que aplica las reglas de transición válidas o rechaza la operación
sin cambiar el estado. Agregar un estado nuevo (por ejemplo, EN_ESPERA_PROVEEDOR)
solo requiere crear una nueva clase, sin tocar los if/else dispersos en varios métodos.
La alternativa descartada fue el patrón **Strategy**: en Strategy, un cliente externo
elige e inyecta explícitamente la estrategia activa desde afuera, intercambiando
algoritmos independientes entre sí. Aquí no hay un cliente eligiendo estrategias: es
la propia solicitud quien determina qué operaciones son válidas según su estado
actual, y el objeto de estado también provoca la transición al estado siguiente como
parte de resolver la operación. Esa auto-transición es incompatible con Strategy,
donde las implementaciones son intercambiables pero no se auto-reemplazan.

### Reflexión — otros tres patrones (opcional)
(1) Para recorrer secuencialmente las solicitudes de un centro de costo sin exponer
su estructura interna, encajaría el patrón **Iterador**, que provee un recorrido
uniforme sobre colecciones sin revelar si son listas, mapas u otra estructura.
(2) Para los tres tipos de comprobante con el mismo esqueleto de impresión
(encabezado, cuerpo, pie) pero con cuerpos diferentes, encajaría el patrón
**Template Method**, donde la clase base define el esqueleto y las subclases
implementan únicamente los pasos variables.
(3) Para guardar y restaurar instantáneas completas del estado de una solicitud
sin exponer sus detalles internos, encajaría el patrón **Memento**: a diferencia
del Comando de la Necesidad 2 (que encapsula operaciones reversibles), el Memento
captura el estado completo del objeto en un instante dado y lo restaura íntegramente,
sin necesidad de conocer qué operaciones se ejecutaron.

## Herramientas utilizadas
- Java 17, Spring Boot 3.2, Apache Maven, JUnit 5
- VS Code con Extension Pack for Java, Git, GitHub

## Conclusiones
La mayor dificultad del laboratorio fue distinguir patrones que estructuralmente
parecen similares pero resuelven problemas fundamentalmente distintos: Cadena de
Responsabilidad y Comando comparten la idea de encapsular comportamiento, pero
difieren en que el primero modela una petición que avanza hasta que alguien la
resuelve, mientras que el segundo modela operaciones reversibles con historial.
De igual forma, Observador y Estado operan sobre cambios de estado, pero el
primero conecta módulos externos que reaccionan a un cambio ya ocurrido, mientras
que el segundo controla qué operaciones son válidas desde adentro de la propia
entidad. Implementar los cuatro patrones en un mismo dominio dejó claro que la
elección correcta no depende de reconocer la "forma" del patrón, sino de
identificar con precisión quién inicia la acción, quién decide, quién reacciona
y si el comportamiento es reversible. Este análisis comparativo es lo que convierte
la elección de un patrón en una decisión de diseño argumentada, no en una
intuición.
