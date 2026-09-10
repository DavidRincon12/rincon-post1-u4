package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class EstadoPendiente implements EstadoSolicitud {
    @Override
    public String aprobar(Solicitud solicitud) {
        solicitud.setEstado("APROBADA");
        return "Aprobada";
    }

    @Override
    public String rechazar(Solicitud solicitud) {
        solicitud.setEstado("RECHAZADA");
        return "Rechazada";
    }

    @Override
    public String ejecutar(Solicitud solicitud) {
        return "Error: debe estar aprobada antes de ejecutarse";
    }

    @Override
    public String cancelar(Solicitud solicitud) {
        solicitud.setEstado("CANCELADA");
        return "Cancelada";
    }
}
