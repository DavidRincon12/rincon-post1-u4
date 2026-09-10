package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class ObservadorDashboard implements ObservadorEstado {
    @Override
    public void alCambiarEstado(Solicitud solicitud, String nuevoEstado) {
        ClientesNotificacion.actualizarDashboardContabilidad(
            solicitud.getId(),
            nuevoEstado,
            solicitud.getMonto()
        );
    }
}
