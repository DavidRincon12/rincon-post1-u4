package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class SupervisorAprobacion extends NivelAprobacion {
    private static final double LIMITE = 2_000_000;

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        if (solicitud.getMonto() <= LIMITE) {
            solicitud.setNivelResolutor("Supervisor de Área");
            solicitud.setEstado("APROBADA");
            return new ResultadoAprobacion(true, "Supervisor de Área", "Dentro del límite del Supervisor");
        }
        return delegar(solicitud);
    }
}
