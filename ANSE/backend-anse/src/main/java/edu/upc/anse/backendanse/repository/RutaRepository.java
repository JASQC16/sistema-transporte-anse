package edu.upc.anse.backendanse.repository;

import edu.upc.anse.backendanse.entity.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Integer> {
    List<Ruta> findByOrigenContainingIgnoreCase(String origen);
    List<Ruta> findByDestinoContainingIgnoreCase(String destino);
}