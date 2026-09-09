package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;

public abstract class NivelAprobacion {
    protected NivelAprobacion siguiente;

    public NivelAprobacion setSiguiente(NivelAprobacion siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    public abstract ResultadoAprobacion evaluar(Solicitud solicitud);

    protected ResultadoAprobacion delegar(Solicitud solicitud) {
        if (siguiente != null) {
            return siguiente.evaluar(solicitud);
        }
        return new ResultadoAprobacion(false, "Sistema", "Sin nivel autorizado para resolver la solicitud");
    }
}
