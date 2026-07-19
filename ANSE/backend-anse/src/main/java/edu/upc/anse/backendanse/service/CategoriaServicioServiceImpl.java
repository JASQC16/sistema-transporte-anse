package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.CategoriaServicio;
import edu.upc.anse.backendanse.repository.CategoriaServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoriaServicioServiceImpl implements CategoriaServicioService {
    private final CategoriaServicioRepository categoriaRepository;

    @Autowired
    public CategoriaServicioServiceImpl(CategoriaServicioRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaServicio crearCategoria(CategoriaServicio categoria) {
        if (categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new RuntimeException("Ya existe una categoría con el nombre: " + categoria.getNombre());
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaServicio obtenerCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaServicio> listarTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaServicio> buscarPorNombre(String nombre) {
        return categoriaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public CategoriaServicio actualizarCategoria(Integer id, CategoriaServicio categoriaActualizada) {
        CategoriaServicio categoria = obtenerCategoriaPorId(id);
        categoria.setNombre(categoriaActualizada.getNombre());
        categoria.setDescripcion(categoriaActualizada.getDescripcion());
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminarCategoria(Integer id) {
        CategoriaServicio categoria = obtenerCategoriaPorId(id);
        categoriaRepository.delete(categoria);
    }

    @Override
    public boolean existeCategoriaPorNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }
}