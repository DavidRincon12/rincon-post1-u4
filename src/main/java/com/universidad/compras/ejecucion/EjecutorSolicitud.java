package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class EjecutorSolicitud {
    private final Solicitud solicitud;
    private final Deque<Comando> historial = new ArrayDeque<>();

    public EjecutorSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public void ejecutar(Comando comando) {
        comando.ejecutar();
        historial.push(comando);
    }

    public void deshacer() {
        if (!historial.isEmpty()) {
            historial.pop().deshacer();
        }
    }

    public List<Comando> getHistorial() {
        return new ArrayList<>(historial);
    }

    public int tamanoHistorial() {
        return historial.size();
    }

    public void marcarComoEjecutada() {
        solicitud.setEstado("EJECUTADA");
    }
}
