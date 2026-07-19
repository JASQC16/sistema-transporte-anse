package edu.upc.anse.backendanse.controller;

import edu.upc.anse.backendanse.dto.BoletaDTO;
import edu.upc.anse.backendanse.entity.Boleta;
import edu.upc.anse.backendanse.entity.Servicio;
import edu.upc.anse.backendanse.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {
    private final BoletaService boletaService;

    @Autowired
    public BoletaController(BoletaService boletaService) {
        this.boletaService = boletaService;
    }

    @GetMapping
    public ResponseEntity<List<BoletaDTO>> listarTodos() {
        List<Boleta> boletas = boletaService.listarTodos();
        List<BoletaDTO> dtos = boletas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletaDTO> obtenerPorId(@PathVariable Integer id) {
        Boleta boleta = boletaService.obtenerBoletaPorId(id);
        return ResponseEntity.ok(convertirADTO(boleta));
    }

    @GetMapping("/servicio/{servicioId}")
    public ResponseEntity<BoletaDTO> obtenerPorServicio(@PathVariable Integer servicioId) {
        Boleta boleta = boletaService.obtenerBoletaPorServicio(servicioId);
        return ResponseEntity.ok(convertirADTO(boleta));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<BoletaDTO>> buscarPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        List<Boleta> boletas = boletaService.buscarPorRangoFechas(inicio, fin);
        List<BoletaDTO> dtos = boletas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<BoletaDTO> crear(@RequestBody BoletaDTO dto) {
        Boleta boleta = new Boleta();

        Servicio servicio = new Servicio();
        servicio.setId(dto.getServicioId());
        boleta.setServicio(servicio);

        boleta.setNumero(dto.getNumero());
        boleta.setFechaEmision(dto.getFechaEmision() != null ? dto.getFechaEmision() : LocalDate.now());
        boleta.setSubtotal(dto.getSubtotal());
        boleta.setIgv(dto.getIgv());
        boleta.setTotal(dto.getTotal());
        boleta.setPdf(dto.getPdf());

        Boleta creado = boletaService.crearBoleta(boleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoletaDTO> actualizar(@PathVariable Integer id,
                                                @RequestBody BoletaDTO dto) {
        Boleta datos = new Boleta();
        datos.setNumero(dto.getNumero());
        datos.setSubtotal(dto.getSubtotal());
        datos.setIgv(dto.getIgv());
        datos.setTotal(dto.getTotal());
        datos.setPdf(dto.getPdf());

        Boleta actualizado = boletaService.actualizarBoleta(id, datos);
        return ResponseEntity.ok(convertirADTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boletaService.eliminarBoleta(id);
        return ResponseEntity.noContent().build();
    }

    private BoletaDTO convertirADTO(Boleta boleta) {
        return new BoletaDTO(
                boleta.getId(),
                boleta.getServicio().getId(),
                boleta.getNumero(),
                boleta.getFechaEmision(),
                boleta.getSubtotal(),
                boleta.getIgv(),
                boleta.getTotal(),
                boleta.getPdf()
        );
    }
}