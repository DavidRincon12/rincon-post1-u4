package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class RevisorCumplimientoAprobacion extends NivelAprobacion {
    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        if ("INTERNACIONAL".equals(solicitud.getCategoria())) {
            solicitud.setNivelResolutor("Revisor de Cumplimiento Normativo");
            solicitud.setEstado("APROBADA");
            return new ResultadoAprobacion(true, "Revisor de Cumplimiento Normativo",
                    "Cumplimiento normativo validado para solicitud internacional");
        }
        return delegar(solicitud);
    }
}
