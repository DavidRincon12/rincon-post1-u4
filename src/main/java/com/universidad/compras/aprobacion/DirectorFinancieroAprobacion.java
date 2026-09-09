package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public class DirectorFinancieroAprobacion extends NivelAprobacion {
    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        solicitud.setNivelResolutor("Director Financiero");
        solicitud.setEstado("APROBADA");
        return new ResultadoAprobacion(true, "Director Financiero", "Autorizado por el Director Financiero");
    }
}
