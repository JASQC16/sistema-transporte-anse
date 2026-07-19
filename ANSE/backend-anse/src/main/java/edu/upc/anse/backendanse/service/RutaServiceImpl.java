package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Ruta;
import edu.upc.anse.backendanse.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class RutaServiceImpl implements RutaService {
    private final RutaRepository rutaRepository;

    @Autowired
    public RutaServiceImpl(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    @Override
    public Ruta crearRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    @Override
    @Transactional(readOnly = true)
    public Ruta obtenerRutaPorId(Integer id) {
        return rutaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ruta> listarTodos() {
        return rutaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ruta> buscarPorOrigen(String origen) {
        return rutaRepository.findByOrigenContainingIgnoreCase(origen);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ruta> buscarPorDestino(String destino) {
        return rutaRepository.findByDestinoContainingIgnoreCase(destino);
    }

    @Override
    public Ruta actualizarRuta(Integer id, Ruta rutaActualizada) {
        Ruta ruta = obtenerRutaPorId(id);
        ruta.setOrigen(rutaActualizada.getOrigen());
        ruta.setDestino(rutaActualizada.getDestino());
        ruta.setDistanciaKm(rutaActualizada.getDistanciaKm());
        ruta.setTiempoEstimado(rutaActualizada.getTiempoEstimado());
        return rutaRepository.save(ruta);
    }

    @Override
    public void eliminarRuta(Integer id) {
        Ruta ruta = obtenerRutaPorId(id);
        rutaRepository.delete(ruta);
    }
}