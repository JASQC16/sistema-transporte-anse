package edu.upc.anse.backendanse.repository;

import edu.upc.anse.backendanse.entity.CategoriaServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaServicioRepository extends JpaRepository<CategoriaServicio, Integer> {
    List<CategoriaServicio> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByNombre(String nombre);
}