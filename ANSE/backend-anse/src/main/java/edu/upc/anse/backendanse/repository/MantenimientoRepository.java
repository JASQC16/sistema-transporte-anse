package edu.upc.anse.backendanse.repository;

import edu.upc.anse.backendanse.entity.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Integer> {
    List<Mantenimiento> findByVehiculoId(Integer vehiculoId);
    List<Mantenimiento> findByFechaBetween(LocalDate inicio, LocalDate fin);
    List<Mantenimiento> findByTipoContainingIgnoreCase(String tipo);
}