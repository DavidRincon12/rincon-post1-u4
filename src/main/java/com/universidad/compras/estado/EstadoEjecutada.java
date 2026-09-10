package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class EstadoEjecutada implements EstadoSolicitud {
    @Override
    public String aprobar(Solicitud solicitud) {
        return "Error: no se puede aprobar una solicitud ejecutada";
    }

    @Override
    public String rechazar(Solicitud solicitud) {
        return "Error: no se puede rechazar una solicitud ejecutada";
    }

    @Override
    public String ejecutar(Solicitud solicitud) {
        return "Error: la solicitud ya fue ejecutada";
    }

    @Override
    public String cancelar(Solicitud solicitud) {
        return "Error: no se puede cancelar una solicitud ejecutada";
    }
}
