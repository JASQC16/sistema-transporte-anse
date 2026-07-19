package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Vehiculo;
import java.util.List;

public interface VehiculoService {
    Vehiculo crearVehiculo(Vehiculo vehiculo);
    Vehiculo obtenerVehiculoPorId(Integer id);
    List<Vehiculo> listarTodos();
    Vehiculo buscarPorPlaca(String placa);
    List<Vehiculo> buscarPorEstado(String estado);
    List<Vehiculo> buscarPorMarca(String marca);
    Vehiculo actualizarVehiculo(Integer id, Vehiculo vehiculo);
    void eliminarVehiculo(Integer id);
    boolean existeVehiculoPorPlaca(String placa);
    List<Vehiculo> listarVehiculosDisponibles();
}