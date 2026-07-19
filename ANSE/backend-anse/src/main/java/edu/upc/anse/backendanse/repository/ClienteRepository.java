package edu.upc.anse.backendanse.repository;

import edu.upc.anse.backendanse.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByRazonSocialContainingIgnoreCase(String razonSocial);
    Optional<Cliente> findByRucDni(String rucDni);
    boolean existsByRucDni(String rucDni);
}