package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class ObservadorCorreo implements ObservadorEstado {
    @Override
    public void alCambiarEstado(Solicitud solicitud, String nuevoEstado) {
        ClientesNotificacion.enviarCorreo(
            solicitud.getSolicitanteEmail(),
            "Actualización de solicitud " + solicitud.getId(),
            "Su solicitud ha cambiado a estado: " + nuevoEstado
        );
    }
}
