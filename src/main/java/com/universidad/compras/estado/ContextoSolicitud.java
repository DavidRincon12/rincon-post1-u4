package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class ContextoSolicitud {
    private final Solicitud solicitud;

    public ContextoSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    private EstadoSolicitud estadoActual() {
        return switch (solicitud.getEstado()) {
            case "PENDIENTE"     -> new EstadoPendiente();
            case "APROBADA"      -> new EstadoAprobada();
            case "RECHAZADA"     -> new EstadoRechazada();
            case "EJECUTADA"     -> new EstadoEjecutada();
            case "CANCELADA"     -> new EstadoCancelada();
            default              -> new EstadoPendiente();
        };
    }

    public String aprobar()  { return estadoActual().aprobar(solicitud); }
    public String rechazar() { return estadoActual().rechazar(solicitud); }
    public String ejecutar() { return estadoActual().ejecutar(solicitud); }
    public String cancelar() { return estadoActual().cancelar(solicitud); }
}
