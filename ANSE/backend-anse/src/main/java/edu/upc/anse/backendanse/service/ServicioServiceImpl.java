package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.*;
import edu.upc.anse.backendanse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ServicioServiceImpl implements ServicioService {
    private final ServicioRepository servicioRepository;
    private final ClienteRepository clienteRepository;
    private final CategoriaServicioRepository categoriaRepository;
    private final RutaRepository rutaRepository;
    private final ConductorRepository conductorRepository;
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public ServicioServiceImpl(ServicioRepository servicioRepository,
                               ClienteRepository clienteRepository,
                               CategoriaServicioRepository categoriaRepository,
                               RutaRepository rutaRepository,
                               ConductorRepository conductorRepository,
                               VehiculoRepository vehiculoRepository) {
        this.servicioRepository = servicioRepository;
        this.clienteRepository = clienteRepository;
        this.categoriaRepository = categoriaRepository;
        this.rutaRepository = rutaRepository;
        this.conductorRepository = conductorRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public Servicio crearServicio(Servicio servicio) {
        // Validar cliente
        if (servicio.getCliente() == null || servicio.getCliente().getId() == null) {
            throw new RuntimeException("El servicio debe tener un cliente válido");
        }
        clienteRepository.findById(servicio.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Validar categoría
        if (servicio.getCategoria() == null || servicio.getCategoria().getId() == null) {
            throw new RuntimeException("El servicio debe tener una categoría válida");
        }
        categoriaRepository.findById(servicio.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Validar ruta
        if (servicio.getRuta() == null || servicio.getRuta().getId() == null) {
            throw new RuntimeException("El servicio debe tener una ruta válida");
        }
        rutaRepository.findById(servicio.getRuta().getId())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        // Validar conductor
        if (servicio.getConductor() == null || servicio.getConductor().getId() == null) {
            throw new RuntimeException("El servicio debe tener un conductor válido");
        }
        Conductor conductor = conductorRepository.findById(servicio.getConductor().getId())
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));
        if (!conductor.getEstado()) {
            throw new RuntimeException("El conductor no está disponible");
        }

        // Validar vehículo
        if (servicio.getVehiculo() == null || servicio.getVehiculo().getId() == null) {
            throw new RuntimeException("El servicio debe tener un vehículo válido");
        }
        Vehiculo vehiculo = vehiculoRepository.findById(servicio.getVehiculo().getId())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        if (!vehiculo.getEstado().equals("Disponible")) {
            throw new RuntimeException("El vehículo no está disponible");
        }

        return servicioRepository.save(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public Servicio obtenerServicioPorId(Integer id) {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> listarTodos() {
        return servicioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> buscarPorEstado(String estado) {
        return servicioRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return servicioRepository.findByFechaServicioBetween(inicio, fin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> buscarPorCliente(Integer clienteId) {
        return servicioRepository.findByClienteId(clienteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> buscarPorConductor(Integer conductorId) {
        return servicioRepository.findByConductorId(conductorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> buscarPorVehiculo(Integer vehiculoId) {
        return servicioRepository.findByVehiculoId(vehiculoId);
    }

    @Override
    public Servicio actualizarServicio(Integer id, Servicio servicioActualizado) {
        Servicio servicio = obtenerServicioPorId(id);
        servicio.setFechaServicio(servicioActualizado.getFechaServicio());
        servicio.setHoraInicio(servicioActualizado.getHoraInicio());
        servicio.setHoraFin(servicioActualizado.getHoraFin());
        servicio.setEstado(servicioActualizado.getEstado());
        servicio.setCosto(servicioActualizado.getCosto());
        servicio.setObservaciones(servicioActualizado.getObservaciones());
        return servicioRepository.save(servicio);
    }

    @Override
    public void eliminarServicio(Integer id) {
        Servicio servicio = obtenerServicioPorId(id);
        servicioRepository.delete(servicio);
    }

    @Override
    public Servicio actualizarEstado(Integer id, String estado) {
        Servicio servicio = obtenerServicioPorId(id);
        servicio.setEstado(estado);
        return servicioRepository.save(servicio);
    }
}