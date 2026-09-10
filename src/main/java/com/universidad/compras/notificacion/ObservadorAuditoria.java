package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class ObservadorAuditoria implements ObservadorEstado {
    @Override
    public void alCambiarEstado(Solicitud solicitud, String nuevoEstado) {
        ClientesNotificacion.registrarAuditoria(
            solicitud.getId(),
            nuevoEstado,
            "Cambio de estado registrado por el sistema"
        );
    }
}
