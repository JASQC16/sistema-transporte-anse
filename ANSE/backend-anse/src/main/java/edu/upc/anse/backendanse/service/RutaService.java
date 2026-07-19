package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Ruta;
import java.util.List;

public interface RutaService {
    Ruta crearRuta(Ruta ruta);
    Ruta obtenerRutaPorId(Integer id);
    List<Ruta> listarTodos();
    List<Ruta> buscarPorOrigen(String origen);
    List<Ruta> buscarPorDestino(String destino);
    Ruta actualizarRuta(Integer id, Ruta ruta);
    void eliminarRuta(Integer id);
}