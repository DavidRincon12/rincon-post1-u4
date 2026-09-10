package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class EstadoCancelada implements EstadoSolicitud {
    @Override
    public String aprobar(Solicitud solicitud) {
        return "Error: no se puede aprobar una solicitud cancelada";
    }

    @Override
    public String rechazar(Solicitud solicitud) {
        return "Error: no se puede rechazar una solicitud cancelada";
    }

    @Override
    public String ejecutar(Solicitud solicitud) {
        return "Error: no se puede ejecutar una solicitud cancelada";
    }

    @Override
    public String cancelar(Solicitud solicitud) {
        return "Error: la solicitud ya fue cancelada";
    }
}
