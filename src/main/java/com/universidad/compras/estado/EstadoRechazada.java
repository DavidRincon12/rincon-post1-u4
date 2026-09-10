package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;

public class EstadoRechazada implements EstadoSolicitud {
    @Override
    public String aprobar(Solicitud solicitud) {
        return "Error: no se puede aprobar una solicitud rechazada";
    }

    @Override
    public String rechazar(Solicitud solicitud) {
        return "Error: la solicitud ya fue rechazada";
    }

    @Override
    public String ejecutar(Solicitud solicitud) {
        return "Error: no se puede ejecutar una solicitud rechazada";
    }

    @Override
    public String cancelar(Solicitud solicitud) {
        return "Error: no se puede cancelar una solicitud rechazada";
    }
}
