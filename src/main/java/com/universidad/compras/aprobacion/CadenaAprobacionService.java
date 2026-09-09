package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;
import org.springframework.stereotype.Service;

@Service
public class CadenaAprobacionService implements ServicioAprobacion {
    private final NivelAprobacion cabeza;

    public CadenaAprobacionService() {
        RevisorCumplimientoAprobacion revisor = new RevisorCumplimientoAprobacion();
        SupervisorAprobacion supervisor = new SupervisorAprobacion();
        GerenteAprobacion gerente = new GerenteAprobacion();
        DirectorFinancieroAprobacion director = new DirectorFinancieroAprobacion();

        revisor.setSiguiente(supervisor);
        supervisor.setSiguiente(gerente);
        gerente.setSiguiente(director);

        this.cabeza = revisor;
    }

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud) {
        return cabeza.evaluar(solicitud);
    }
}
