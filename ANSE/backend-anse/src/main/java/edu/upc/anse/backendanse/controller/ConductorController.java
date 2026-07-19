package edu.upc.anse.backendanse.controller;

import edu.upc.anse.backendanse.dto.ConductorDTO;
import edu.upc.anse.backendanse.entity.Conductor;
import edu.upc.anse.backendanse.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/conductores")
public class ConductorController {
    private final ConductorService conductorService;

    @Autowired
    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    @GetMapping
    public ResponseEntity<List<ConductorDTO>> listarTodos() {
        List<Conductor> conductores = conductorService.listarTodos();
        List<ConductorDTO> dtos = conductores.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<ConductorDTO>> listarDisponibles() {
        List<Conductor> conductores = conductorService.listarConductoresDisponibles();
        List<ConductorDTO> dtos = conductores.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConductorDTO> obtenerPorId(@PathVariable Integer id) {
        Conductor conductor = conductorService.obtenerConductorPorId(id);
        return ResponseEntity.ok(convertirADTO(conductor));
    }

    @GetMapping("/licencia/{licencia}")
    public ResponseEntity<ConductorDTO> buscarPorLicencia(@PathVariable String licencia) {
        Conductor conductor = conductorService.buscarPorLicencia(licencia);
        return ResponseEntity.ok(convertirADTO(conductor));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ConductorDTO>> buscarPorNombres(@RequestParam String nombres) {
        List<Conductor> conductores = conductorService.buscarPorNombres(nombres);
        List<ConductorDTO> dtos = conductores.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ConductorDTO> crear(@RequestBody ConductorDTO dto) {
        Conductor conductor = new Conductor();
        conductor.setNombres(dto.getNombres());
        conductor.setApellidos(dto.getApellidos());
        conductor.setLicencia(dto.getLicencia());
        conductor.setTelefono(dto.getTelefono());
        conductor.setCategoriaLicencia(dto.getCategoriaLicencia());
        conductor.setEstado(dto.getEstado() != null ? dto.getEstado() : true);

        Conductor creado = conductorService.crearConductor(conductor);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConductorDTO> actualizar(@PathVariable Integer id,
                                                   @RequestBody ConductorDTO dto) {
        Conductor datos = new Conductor();
        datos.setNombres(dto.getNombres());
        datos.setApellidos(dto.getApellidos());
        datos.setTelefono(dto.getTelefono());
        datos.setCategoriaLicencia(dto.getCategoriaLicencia());
        datos.setEstado(dto.getEstado());

        Conductor actualizado = conductorService.actualizarConductor(id, datos);
        return ResponseEntity.ok(convertirADTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        conductorService.eliminarConductor(id);
        return ResponseEntity.noContent().build();
    }

    private ConductorDTO convertirADTO(Conductor conductor) {
        return new ConductorDTO(
                conductor.getId(),
                conductor.getNombres(),
                conductor.getApellidos(),
                conductor.getLicencia(),
                conductor.getTelefono(),
                conductor.getCategoriaLicencia(),
                conductor.getEstado()
        );
    }
}