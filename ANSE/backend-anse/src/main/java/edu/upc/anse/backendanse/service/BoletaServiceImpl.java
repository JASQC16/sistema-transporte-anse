package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Boleta;
import edu.upc.anse.backendanse.entity.Servicio;
import edu.upc.anse.backendanse.repository.BoletaRepository;
import edu.upc.anse.backendanse.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class BoletaServiceImpl implements BoletaService {
    private final BoletaRepository boletaRepository;
    private final ServicioRepository servicioRepository;

    @Autowired
    public BoletaServiceImpl(BoletaRepository boletaRepository, ServicioRepository servicioRepository) {
        this.boletaRepository = boletaRepository;
        this.servicioRepository = servicioRepository;
    }

    @Override
    public Boleta crearBoleta(Boleta boleta) {
        if (boleta.getServicio() == null || boleta.getServicio().getId() == null) {
            throw new RuntimeException("La boleta debe tener un servicio válido");
        }

        Servicio servicio = servicioRepository.findById(boleta.getServicio().getId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        if (boletaRepository.findByServicioId(servicio.getId()).isPresent()) {
            throw new RuntimeException("Ya existe una boleta para este servicio");
        }

        if (boletaRepository.existsByNumero(boleta.getNumero())) {
            throw new RuntimeException("Ya existe una boleta con el número: " + boleta.getNumero());
        }

        if (boleta.getIgv() == null || boleta.getTotal() == null) {
            BigDecimal subtotal = boleta.getSubtotal() != null ? boleta.getSubtotal() : servicio.getCosto();
            BigDecimal igv = subtotal.multiply(new BigDecimal("0.18")).setScale(2, RoundingMode.HALF_UP);
            BigDecimal total = subtotal.add(igv);
            boleta.setSubtotal(subtotal);
            boleta.setIgv(igv);
            boleta.setTotal(total);
        }

        if (boleta.getFechaEmision() == null) {
            boleta.setFechaEmision(LocalDate.now());
        }

        return boletaRepository.save(boleta);
    }

    @Override
    @Transactional(readOnly = true)
    public Boleta obtenerBoletaPorId(Integer id) {
        return boletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boleta no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boleta> listarTodos() {
        return boletaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Boleta obtenerBoletaPorServicio(Integer servicioId) {
        return boletaRepository.findByServicioId(servicioId)
                .orElseThrow(() -> new RuntimeException("No existe boleta para el servicio ID: " + servicioId));
    }

    @Override
    @Transactional(readOnly = true)
    public Boleta obtenerBoletaPorNumero(String numero) {
        return boletaRepository.findByNumero(numero)
                .orElseThrow(() -> new RuntimeException("Boleta no encontrada con número: " + numero));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boleta> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return boletaRepository.findByFechaEmisionBetween(inicio, fin);
    }

    @Override
    public Boleta actualizarBoleta(Integer id, Boleta boletaActualizada) {
        Boleta boleta = obtenerBoletaPorId(id);
        boleta.setNumero(boletaActualizada.getNumero());
        boleta.setSubtotal(boletaActualizada.getSubtotal());
        boleta.setIgv(boletaActualizada.getIgv());
        boleta.setTotal(boletaActualizada.getTotal());
        boleta.setPdf(boletaActualizada.getPdf());
        return boletaRepository.save(boleta);
    }

    @Override
    public void eliminarBoleta(Integer id) {
        Boleta boleta = obtenerBoletaPorId(id);
        boletaRepository.delete(boleta);
    }

    @Override
    public boolean existeBoletaPorNumero(String numero) {
        return boletaRepository.existsByNumero(numero);
    }
}