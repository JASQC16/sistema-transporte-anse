package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.CategoriaServicio;
import java.util.List;

public interface CategoriaServicioService {
    CategoriaServicio crearCategoria(CategoriaServicio categoria);
    CategoriaServicio obtenerCategoriaPorId(Integer id);
    List<CategoriaServicio> listarTodos();
    List<CategoriaServicio> buscarPorNombre(String nombre);
    CategoriaServicio actualizarCategoria(Integer id, CategoriaServicio categoria);
    void eliminarCategoria(Integer id);
    boolean existeCategoriaPorNombre(String nombre);
}