package com.universidad.compras.ejecucion;

import com.universidad.compras.modelo.Solicitud;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EjecucionSolicitudTest {

    @Test
    void ejecutarReservaPresupuestoYGeneraOrden() {
        Solicitud s = new Solicitud("S-010", "ana@udes.edu.co", 3000000, "SOFTWARE", "CC-100");
        s.setEstado("APROBADA");
        EjecutorSolicitud ejecutor = new EjecutorSolicitud(s);
        ejecutor.ejecutar(new ReservarPresupuestoComando(new PresupuestoService(), s.getCentroCosto(), s.getMonto()));
        ejecutor.ejecutar(new GenerarOrdenComando(new OrdenCompraService(), s.getId(), "Proveedor-ABC"));
        ejecutor.marcarComoEjecutada();
        assertEquals("EJECUTADA", s.getEstado());
    }

    @Test
    void deshacerSoloLaUltimaOperacionNoAfectaLaAnterior() {
        Solicitud s = new Solicitud("S-011", "luis@udes.edu.co", 4000000, "MATERIAL_OFICINA", "CC-200");
        EjecutorSolicitud ejecutor = new EjecutorSolicitud(s);
        assertDoesNotThrow(() -> {
            ejecutor.ejecutar(new ReservarPresupuestoComando(new PresupuestoService(), s.getCentroCosto(), s.getMonto()));
            ejecutor.ejecutar(new GenerarOrdenComando(new OrdenCompraService(), s.getId(), "Proveedor-XYZ"));
            ejecutor.deshacer();
            assertEquals(1, ejecutor.tamanoHistorial());
        });
    }

    @Test
    void elHistorialConservaTodasLasOperacionesNoSoloLaUltima() {
        Solicitud s = new Solicitud("S-012", "test@udes.edu.co", 2000000, "SOFTWARE", "CC-300");
        EjecutorSolicitud ejecutor = new EjecutorSolicitud(s);
        ejecutor.ejecutar(new ReservarPresupuestoComando(new PresupuestoService(), s.getCentroCosto(), s.getMonto()));
        ejecutor.ejecutar(new GenerarOrdenComando(new OrdenCompraService(), s.getId(), "Proveedor-123"));
        assertEquals(2, ejecutor.tamanoHistorial());
    }
}
