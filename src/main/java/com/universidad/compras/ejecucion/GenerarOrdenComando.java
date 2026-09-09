package com.universidad.compras.ejecucion;

public class GenerarOrdenComando implements Comando {
    private final OrdenCompraService ordenCompraService;
    private final String solicitudId;
    private final String proveedor;
    private String numeroOrden;

    public GenerarOrdenComando(OrdenCompraService ordenCompraService, String solicitudId, String proveedor) {
        this.ordenCompraService = ordenCompraService;
        this.solicitudId = solicitudId;
        this.proveedor = proveedor;
    }

    @Override
    public void ejecutar() {
        this.numeroOrden = ordenCompraService.generar(solicitudId, proveedor);
    }

    @Override
    public void deshacer() {
        if (numeroOrden != null) {
            ordenCompraService.cancelar(numeroOrden);
        }
    }

    @Override
    public String getDescripcion() {
        return "Generar orden de compra para solicitud " + solicitudId + " con proveedor " + proveedor;
    }
}
