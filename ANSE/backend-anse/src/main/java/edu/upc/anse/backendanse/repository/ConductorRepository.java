package edu.upc.anse.backendanse.repository;

import edu.upc.anse.backendanse.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Integer> {
    List<Conductor> findByNombresContainingIgnoreCase(String nombres);
    List<Conductor> findByApellidosContainingIgnoreCase(String apellidos);
    Optional<Conductor> findByLicencia(String licencia);
    boolean existsByLicencia(String licencia);
    List<Conductor> findByEstadoTrue();
}