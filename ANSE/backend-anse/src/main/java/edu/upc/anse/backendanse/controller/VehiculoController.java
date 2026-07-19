package edu.upc.anse.backendanse.controller;

import edu.upc.anse.backendanse.dto.VehiculoDTO;
import edu.upc.anse.backendanse.entity.Vehiculo;
import edu.upc.anse.backendanse.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    private final VehiculoService vehiculoService;

    @Autowired
    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> listarTodos() {
        List<Vehiculo> vehiculos = vehiculoService.listarTodos();
        List<VehiculoDTO> dtos = vehiculos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<VehiculoDTO>> listarDisponibles() {
        List<Vehiculo> vehiculos = vehiculoService.listarVehiculosDisponibles();
        List<VehiculoDTO> dtos = vehiculos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> obtenerPorId(@PathVariable Integer id) {
        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id);
        return ResponseEntity.ok(convertirADTO(vehiculo));
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<VehiculoDTO> buscarPorPlaca(@PathVariable String placa) {
        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
        return ResponseEntity.ok(convertirADTO(vehiculo));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<VehiculoDTO>> buscarPorEstado(@PathVariable String estado) {
        List<Vehiculo> vehiculos = vehiculoService.buscarPorEstado(estado);
        List<VehiculoDTO> dtos = vehiculos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<VehiculoDTO> crear(@RequestBody VehiculoDTO dto) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(dto.getPlaca());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setCapacidad(dto.getCapacidad());
        vehiculo.setTipoCarga(dto.getTipoCarga());
        vehiculo.setEstado(dto.getEstado());

        Vehiculo creado = vehiculoService.crearVehiculo(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoDTO> actualizar(@PathVariable Integer id,
                                                  @RequestBody VehiculoDTO dto) {
        Vehiculo datos = new Vehiculo();
        datos.setPlaca(dto.getPlaca());
        datos.setMarca(dto.getMarca());
        datos.setModelo(dto.getModelo());
        datos.setAnio(dto.getAnio());
        datos.setCapacidad(dto.getCapacidad());
        datos.setTipoCarga(dto.getTipoCarga());
        datos.setEstado(dto.getEstado());

        Vehiculo actualizado = vehiculoService.actualizarVehiculo(id, datos);
        return ResponseEntity.ok(convertirADTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        vehiculoService.eliminarVehiculo(id);
        return ResponseEntity.noContent().build();
    }

    private VehiculoDTO convertirADTO(Vehiculo vehiculo) {
        return new VehiculoDTO(
                vehiculo.getId(),
                vehiculo.getPlaca(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getAnio(),
                vehiculo.getCapacidad(),
                vehiculo.getTipoCarga(),
                vehiculo.getEstado()
        );
    }
}