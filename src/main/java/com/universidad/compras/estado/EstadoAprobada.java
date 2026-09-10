package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class EstadoAprobada implements EstadoSolicitud {
    @Override
    public String aprobar(Solicitud solicitud) {
        return "Error: la solicitud ya fue aprobada";
    }

    @Override
    public String rechazar(Solicitud solicitud) {
        solicitud.setEstado("RECHAZADA");
        return "Rechazada";
    }

    @Override
    public String ejecutar(Solicitud solicitud) {
        solicitud.setEstado("EJECUTADA");
        return "Ejecutada";
    }

    @Override
    public String cancelar(Solicitud solicitud) {
        solicitud.setEstado("CANCELADA");
        return "Cancelada";
    }
}
