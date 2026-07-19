package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Conductor;
import edu.upc.anse.backendanse.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ConductorServiceImpl implements ConductorService {
    private final ConductorRepository conductorRepository;

    @Autowired
    public ConductorServiceImpl(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    @Override
    public Conductor crearConductor(Conductor conductor) {
        if (conductorRepository.existsByLicencia(conductor.getLicencia())) {
            throw new RuntimeException("Ya existe un conductor con la licencia: " + conductor.getLicencia());
        }
        return conductorRepository.save(conductor);
    }

    @Override
    @Transactional(readOnly = true)
    public Conductor obtenerConductorPorId(Integer id) {
        return conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conductor> listarTodos() {
        return conductorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conductor> buscarPorNombres(String nombres) {
        return conductorRepository.findByNombresContainingIgnoreCase(nombres);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conductor> buscarPorApellidos(String apellidos) {
        return conductorRepository.findByApellidosContainingIgnoreCase(apellidos);
    }

    @Override
    @Transactional(readOnly = true)
    public Conductor buscarPorLicencia(String licencia) {
        return conductorRepository.findByLicencia(licencia)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado con licencia: " + licencia));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conductor> listarConductoresDisponibles() {
        return conductorRepository.findByEstadoTrue();
    }

    @Override
    public Conductor actualizarConductor(Integer id, Conductor conductorActualizado) {
        Conductor conductor = obtenerConductorPorId(id);
        conductor.setNombres(conductorActualizado.getNombres());
        conductor.setApellidos(conductorActualizado.getApellidos());
        conductor.setTelefono(conductorActualizado.getTelefono());
        conductor.setCategoriaLicencia(conductorActualizado.getCategoriaLicencia());
        conductor.setEstado(conductorActualizado.getEstado());
        return conductorRepository.save(conductor);
    }

    @Override
    public void eliminarConductor(Integer id) {
        Conductor conductor = obtenerConductorPorId(id);
        conductorRepository.delete(conductor);
    }

    @Override
    public boolean existeConductorPorLicencia(String licencia) {
        return conductorRepository.existsByLicencia(licencia);
    }
}