package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Mantenimiento;
import java.time.LocalDate;
import java.util.List;

public interface MantenimientoService {
    Mantenimiento crearMantenimiento(Mantenimiento mantenimiento);
    Mantenimiento obtenerMantenimientoPorId(Integer id);
    List<Mantenimiento> listarTodos();
    List<Mantenimiento> buscarPorVehiculo(Integer vehiculoId);
    List<Mantenimiento> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);
    List<Mantenimiento> buscarPorTipo(String tipo);
    Mantenimiento actualizarMantenimiento(Integer id, Mantenimiento mantenimiento);
    void eliminarMantenimiento(Integer id);
}