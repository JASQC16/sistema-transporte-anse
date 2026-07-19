package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Conductor;
import java.util.List;

public interface ConductorService {
    Conductor crearConductor(Conductor conductor);
    Conductor obtenerConductorPorId(Integer id);
    List<Conductor> listarTodos();
    List<Conductor> buscarPorNombres(String nombres);
    List<Conductor> buscarPorApellidos(String apellidos);
    Conductor buscarPorLicencia(String licencia);
    List<Conductor> listarConductoresDisponibles();
    Conductor actualizarConductor(Integer id, Conductor conductor);
    void eliminarConductor(Integer id);
    boolean existeConductorPorLicencia(String licencia);
}