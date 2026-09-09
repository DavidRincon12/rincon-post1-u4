package com.universidad.compras.ejecucion;

public class ReservarPresupuestoComando implements Comando {
    private final PresupuestoService presupuestoService;
    private final String centroCosto;
    private final double monto;

    public ReservarPresupuestoComando(PresupuestoService presupuestoService, String centroCosto, double monto) {
        this.presupuestoService = presupuestoService;
        this.centroCosto = centroCosto;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        presupuestoService.reservar(centroCosto, monto);
    }

    @Override
    public void deshacer() {
        presupuestoService.liberar(centroCosto, monto);
    }

    @Override
    public String getDescripcion() {
        return "Reservar presupuesto $" + monto + " en " + centroCosto;
    }
}
