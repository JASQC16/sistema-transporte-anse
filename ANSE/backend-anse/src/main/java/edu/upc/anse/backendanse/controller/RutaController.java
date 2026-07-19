package edu.upc.anse.backendanse.controller;

import edu.upc.anse.backendanse.dto.RutaDTO;
import edu.upc.anse.backendanse.entity.Ruta;
import edu.upc.anse.backendanse.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {
    private final RutaService rutaService;

    @Autowired
    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @GetMapping
    public ResponseEntity<List<RutaDTO>> listarTodos() {
        List<Ruta> rutas = rutaService.listarTodos();
        List<RutaDTO> dtos = rutas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutaDTO> obtenerPorId(@PathVariable Integer id) {
        Ruta ruta = rutaService.obtenerRutaPorId(id);
        return ResponseEntity.ok(convertirADTO(ruta));
    }

    @GetMapping("/origen")
    public ResponseEntity<List<RutaDTO>> buscarPorOrigen(@RequestParam String origen) {
        List<Ruta> rutas = rutaService.buscarPorOrigen(origen);
        List<RutaDTO> dtos = rutas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/destino")
    public ResponseEntity<List<RutaDTO>> buscarPorDestino(@RequestParam String destino) {
        List<Ruta> rutas = rutaService.buscarPorDestino(destino);
        List<RutaDTO> dtos = rutas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<RutaDTO> crear(@RequestBody RutaDTO dto) {
        Ruta ruta = new Ruta();
        ruta.setOrigen(dto.getOrigen());
        ruta.setDestino(dto.getDestino());
        ruta.setDistanciaKm(dto.getDistanciaKm());
        ruta.setTiempoEstimado(dto.getTiempoEstimado());

        Ruta creado = rutaService.crearRuta(ruta);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RutaDTO> actualizar(@PathVariable Integer id,
                                              @RequestBody RutaDTO dto) {
        Ruta datos = new Ruta();
        datos.setOrigen(dto.getOrigen());
        datos.setDestino(dto.getDestino());
        datos.setDistanciaKm(dto.getDistanciaKm());
        datos.setTiempoEstimado(dto.getTiempoEstimado());

        Ruta actualizado = rutaService.actualizarRuta(id, datos);
        return ResponseEntity.ok(convertirADTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rutaService.eliminarRuta(id);
        return ResponseEntity.noContent().build();
    }

    private RutaDTO convertirADTO(Ruta ruta) {
        return new RutaDTO(
                ruta.getId(),
                ruta.getOrigen(),
                ruta.getDestino(),
                ruta.getDistanciaKm(),
                ruta.getTiempoEstimado()
        );
    }
}