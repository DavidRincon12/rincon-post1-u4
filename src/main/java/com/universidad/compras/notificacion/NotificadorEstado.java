package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;
import java.util.ArrayList;
import java.util.List;

public class NotificadorEstado {
    private final List<ObservadorEstado> observadores = new ArrayList<>();

    public void registrar(ObservadorEstado observador) {
        observadores.add(observador);
    }

    public void notificar(Solicitud solicitud, String nuevoEstado) {
        solicitud.setEstado(nuevoEstado);
        for (ObservadorEstado obs : observadores) {
            obs.alCambiarEstado(solicitud, nuevoEstado);
        }
    }
}
