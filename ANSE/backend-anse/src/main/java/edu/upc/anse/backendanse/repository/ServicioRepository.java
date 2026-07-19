package edu.upc.anse.backendanse.repository;

import edu.upc.anse.backendanse.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    List<Servicio> findByEstado(String estado);
    List<Servicio> findByFechaServicioBetween(LocalDate inicio, LocalDate fin);
    List<Servicio> findByClienteId(Integer clienteId);
    List<Servicio> findByConductorId(Integer conductorId);
    List<Servicio> findByVehiculoId(Integer vehiculoId);

    @Query("SELECT s FROM Servicio s WHERE s.fechaServicio >= :fecha AND s.estado = :estado")
    List<Servicio> findServiciosFromDateWithState(@Param("fecha") LocalDate fecha, @Param("estado") String estado);
}