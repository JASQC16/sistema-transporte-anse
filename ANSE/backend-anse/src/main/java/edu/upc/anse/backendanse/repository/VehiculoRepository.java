package edu.upc.anse.backendanse.repository;

import edu.upc.anse.backendanse.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    Optional<Vehiculo> findByPlaca(String placa);
    boolean existsByPlaca(String placa);
    List<Vehiculo> findByEstado(String estado);
    List<Vehiculo> findByMarcaContainingIgnoreCase(String marca);
}