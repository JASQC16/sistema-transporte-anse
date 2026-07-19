package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Mantenimiento;
import edu.upc.anse.backendanse.entity.Vehiculo;
import edu.upc.anse.backendanse.repository.MantenimientoRepository;
import edu.upc.anse.backendanse.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class MantenimientoServiceImpl implements MantenimientoService {
    private final MantenimientoRepository mantenimientoRepository;
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public MantenimientoServiceImpl(MantenimientoRepository mantenimientoRepository, VehiculoRepository vehiculoRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public Mantenimiento crearMantenimiento(Mantenimiento mantenimiento) {
        if (mantenimiento.getVehiculo() == null || mantenimiento.getVehiculo().getId() == null) {
            throw new RuntimeException("El mantenimiento debe tener un vehículo válido");
        }

        Vehiculo vehiculo = vehiculoRepository.findById(mantenimiento.getVehiculo().getId())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        vehiculo.setEstado("En Mantenimiento");
        vehiculoRepository.save(vehiculo);

        return mantenimientoRepository.save(mantenimiento);
    }

    @Override
    @Transactional(readOnly = true)
    public Mantenimiento obtenerMantenimientoPorId(Integer id) {
        return mantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mantenimiento> listarTodos() {
        return mantenimientoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mantenimiento> buscarPorVehiculo(Integer vehiculoId) {
        return mantenimientoRepository.findByVehiculoId(vehiculoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mantenimiento> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return mantenimientoRepository.findByFechaBetween(inicio, fin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mantenimiento> buscarPorTipo(String tipo) {
        return mantenimientoRepository.findByTipoContainingIgnoreCase(tipo);
    }

    @Override
    public Mantenimiento actualizarMantenimiento(Integer id, Mantenimiento mantenimientoActualizado) {
        Mantenimiento mantenimiento = obtenerMantenimientoPorId(id);
        mantenimiento.setFecha(mantenimientoActualizado.getFecha());
        mantenimiento.setTipo(mantenimientoActualizado.getTipo());
        mantenimiento.setDescripcion(mantenimientoActualizado.getDescripcion());
        mantenimiento.setCosto(mantenimientoActualizado.getCosto());
        mantenimiento.setKilometraje(mantenimientoActualizado.getKilometraje());
        return mantenimientoRepository.save(mantenimiento);
    }

    @Override
    public void eliminarMantenimiento(Integer id) {
        Mantenimiento mantenimiento = obtenerMantenimientoPorId(id);
        mantenimientoRepository.delete(mantenimiento);
    }
}