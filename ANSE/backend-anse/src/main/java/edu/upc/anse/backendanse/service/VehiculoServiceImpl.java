package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Vehiculo;
import edu.upc.anse.backendanse.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class VehiculoServiceImpl implements VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        if (vehiculoRepository.existsByPlaca(vehiculo.getPlaca())) {
            throw new RuntimeException("Ya existe un vehículo con la placa: " + vehiculo.getPlaca());
        }
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    @Transactional(readOnly = true)
    public Vehiculo obtenerVehiculoPorId(Integer id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> listarTodos() {
        return vehiculoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Vehiculo buscarPorPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con placa: " + placa));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> buscarPorEstado(String estado) {
        return vehiculoRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> buscarPorMarca(String marca) {
        return vehiculoRepository.findByMarcaContainingIgnoreCase(marca);
    }

    @Override
    public Vehiculo actualizarVehiculo(Integer id, Vehiculo vehiculoActualizado) {
        Vehiculo vehiculo = obtenerVehiculoPorId(id);
        vehiculo.setPlaca(vehiculoActualizado.getPlaca());
        vehiculo.setMarca(vehiculoActualizado.getMarca());
        vehiculo.setModelo(vehiculoActualizado.getModelo());
        vehiculo.setAnio(vehiculoActualizado.getAnio());
        vehiculo.setCapacidad(vehiculoActualizado.getCapacidad());
        vehiculo.setTipoCarga(vehiculoActualizado.getTipoCarga());
        vehiculo.setEstado(vehiculoActualizado.getEstado());
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public void eliminarVehiculo(Integer id) {
        Vehiculo vehiculo = obtenerVehiculoPorId(id);
        vehiculoRepository.delete(vehiculo);
    }

    @Override
    public boolean existeVehiculoPorPlaca(String placa) {
        return vehiculoRepository.existsByPlaca(placa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> listarVehiculosDisponibles() {
        return vehiculoRepository.findByEstado("Disponible");
    }
}