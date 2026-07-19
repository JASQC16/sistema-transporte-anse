package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Servicio;
import java.time.LocalDate;
import java.util.List;

public interface ServicioService {
    Servicio crearServicio(Servicio servicio);
    Servicio obtenerServicioPorId(Integer id);
    List<Servicio> listarTodos();
    List<Servicio> buscarPorEstado(String estado);
    List<Servicio> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);
    List<Servicio> buscarPorCliente(Integer clienteId);
    List<Servicio> buscarPorConductor(Integer conductorId);
    List<Servicio> buscarPorVehiculo(Integer vehiculoId);
    Servicio actualizarServicio(Integer id, Servicio servicio);
    void eliminarServicio(Integer id);
    Servicio actualizarEstado(Integer id, String estado);
}