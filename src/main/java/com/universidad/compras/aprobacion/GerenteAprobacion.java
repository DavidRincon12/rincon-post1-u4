package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class GerenteAprobacion extends NivelAprobacion {
    private static final double LIMITE = 10_000_000;

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        if (solicitud.getMonto() <= LIMITE) {
            solicitud.setNivelResolutor("Gerente de Área");
            solicitud.setEstado("APROBADA");
            return new ResultadoAprobacion(true, "Gerente de Área", "Dentro del límite del Gerente");
        }
        return delegar(solicitud);
    }
}
