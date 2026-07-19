package edu.upc.anse.backendanse.repository;

import edu.upc.anse.backendanse.entity.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Integer> {
    Optional<Boleta> findByServicioId(Integer servicioId);
    List<Boleta> findByFechaEmisionBetween(LocalDate inicio, LocalDate fin);
    Optional<Boleta> findByNumero(String numero);
    boolean existsByNumero(String numero);
}