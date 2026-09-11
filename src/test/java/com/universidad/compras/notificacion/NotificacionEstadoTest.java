package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NotificacionEstadoTest {

    @Test
    void cambiarEstadoDisparaLasTresReaccionesSinLanzarExcepcion() {
        Solicitud s = new Solicitud("S-020", "ana@udes.edu.co", 2500000, "SOFTWARE", "CC-100");
        NotificadorEstado mecanismo = new NotificadorEstado();
        mecanismo.registrar(new ObservadorCorreo());
        mecanismo.registrar(new ObservadorDashboard());
        mecanismo.registrar(new ObservadorAuditoria());
        assertDoesNotThrow(() -> mecanismo.notificar(s, "APROBADA"));
    }

    @Test
    void agregarUnCuartoSuscriptorDePruebaNoRequiereModificarElMecanismo() {
        Solicitud s = new Solicitud("S-021", "test@udes.edu.co", 1000000, "SOFTWARE", "CC-100");
        NotificadorEstado mecanismo = new NotificadorEstado();
        mecanismo.registrar(new ObservadorCorreo());
        mecanismo.registrar(new ObservadorDashboard());
        mecanismo.registrar(new ObservadorAuditoria());

        List<String> estadosCapturados = new ArrayList<>();
        mecanismo.registrar((solicitud, nuevoEstado) -> estadosCapturados.add(nuevoEstado));

        mecanismo.notificar(s, "RECHAZADA");
        assertFalse(estadosCapturados.isEmpty());
        assertEquals("RECHAZADA", estadosCapturados.get(0));
    }
}
