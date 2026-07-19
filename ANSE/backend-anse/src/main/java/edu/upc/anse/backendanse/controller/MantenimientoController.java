package edu.upc.anse.backendanse.controller;

import edu.upc.anse.backendanse.dto.MantenimientoDTO;
import edu.upc.anse.backendanse.entity.Mantenimiento;
import edu.upc.anse.backendanse.entity.Vehiculo;
import edu.upc.anse.backendanse.service.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mantenimientos")
public class MantenimientoController {
    private final MantenimientoService mantenimientoService;

    @Autowired
    public MantenimientoController(MantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

    @GetMapping
    public ResponseEntity<List<MantenimientoDTO>> listarTodos() {
        List<Mantenimiento> mantenimientos = mantenimientoService.listarTodos();
        List<MantenimientoDTO> dtos = mantenimientos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoDTO> obtenerPorId(@PathVariable Integer id) {
        Mantenimiento mantenimiento = mantenimientoService.obtenerMantenimientoPorId(id);
        return ResponseEntity.ok(convertirADTO(mantenimiento));
    }

    @GetMapping("/vehiculo/{vehiculoId}")
    public ResponseEntity<List<MantenimientoDTO>> buscarPorVehiculo(@PathVariable Integer vehiculoId) {
        List<Mantenimiento> mantenimientos = mantenimientoService.buscarPorVehiculo(vehiculoId);
        List<MantenimientoDTO> dtos = mantenimientos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<MantenimientoDTO>> buscarPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        List<Mantenimiento> mantenimientos = mantenimientoService.buscarPorRangoFechas(inicio, fin);
        List<MantenimientoDTO> dtos = mantenimientos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<MantenimientoDTO> crear(@RequestBody MantenimientoDTO dto) {
        Mantenimiento mantenimiento = new Mantenimiento();

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(dto.getVehiculoId());
        mantenimiento.setVehiculo(vehiculo);

        mantenimiento.setFecha(dto.getFecha());
        mantenimiento.setTipo(dto.getTipo());
        mantenimiento.setDescripcion(dto.getDescripcion());
        mantenimiento.setCosto(dto.getCosto());
        mantenimiento.setKilometraje(dto.getKilometraje());

        Mantenimiento creado = mantenimientoService.crearMantenimiento(mantenimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MantenimientoDTO> actualizar(@PathVariable Integer id,
                                                       @RequestBody MantenimientoDTO dto) {
        Mantenimiento datos = new Mantenimiento();
        datos.setFecha(dto.getFecha());
        datos.setTipo(dto.getTipo());
        datos.setDescripcion(dto.getDescripcion());
        datos.setCosto(dto.getCosto());
        datos.setKilometraje(dto.getKilometraje());

        Mantenimiento actualizado = mantenimientoService.actualizarMantenimiento(id, datos);
        return ResponseEntity.ok(convertirADTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        mantenimientoService.eliminarMantenimiento(id);
        return ResponseEntity.noContent().build();
    }

    private MantenimientoDTO convertirADTO(Mantenimiento mantenimiento) {
        return new MantenimientoDTO(
                mantenimiento.getId(),
                mantenimiento.getVehiculo().getId(),
                mantenimiento.getVehiculo().getPlaca(),
                mantenimiento.getFecha(),
                mantenimiento.getTipo(),
                mantenimiento.getDescripcion(),
                mantenimiento.getCosto(),
                mantenimiento.getKilometraje()
        );
    }
}