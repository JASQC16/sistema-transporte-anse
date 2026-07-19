package edu.upc.anse.backendanse.controller;

import edu.upc.anse.backendanse.dto.ServicioDTO;
import edu.upc.anse.backendanse.entity.*;
import edu.upc.anse.backendanse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {
    private final ServicioService servicioService;
    private final ClienteService clienteService;
    private final CategoriaServicioService categoriaService;
    private final RutaService rutaService;
    private final ConductorService conductorService;
    private final VehiculoService vehiculoService;

    @Autowired
    public ServicioController(ServicioService servicioService,
                              ClienteService clienteService,
                              CategoriaServicioService categoriaService,
                              RutaService rutaService,
                              ConductorService conductorService,
                              VehiculoService vehiculoService) {
        this.servicioService = servicioService;
        this.clienteService = clienteService;
        this.categoriaService = categoriaService;
        this.rutaService = rutaService;
        this.conductorService = conductorService;
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> listarTodos() {
        List<Servicio> servicios = servicioService.listarTodos();
        List<ServicioDTO> dtos = servicios.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> obtenerPorId(@PathVariable Integer id) {
        Servicio servicio = servicioService.obtenerServicioPorId(id);
        return ResponseEntity.ok(convertirADTO(servicio));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ServicioDTO>> buscarPorEstado(@PathVariable String estado) {
        List<Servicio> servicios = servicioService.buscarPorEstado(estado);
        List<ServicioDTO> dtos = servicios.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<ServicioDTO>> buscarPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        List<Servicio> servicios = servicioService.buscarPorRangoFechas(inicio, fin);
        List<ServicioDTO> dtos = servicios.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ServicioDTO>> buscarPorCliente(@PathVariable Integer clienteId) {
        List<Servicio> servicios = servicioService.buscarPorCliente(clienteId);
        List<ServicioDTO> dtos = servicios.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> crear(@RequestBody ServicioDTO dto) {
        Servicio servicio = new Servicio();

        Cliente cliente = new Cliente();
        cliente.setId(dto.getClienteId());
        servicio.setCliente(cliente);

        CategoriaServicio categoria = new CategoriaServicio();
        categoria.setId(dto.getCategoriaId());
        servicio.setCategoria(categoria);

        Ruta ruta = new Ruta();
        ruta.setId(dto.getRutaId());
        servicio.setRuta(ruta);

        Conductor conductor = new Conductor();
        conductor.setId(dto.getConductorId());
        servicio.setConductor(conductor);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(dto.getVehiculoId());
        servicio.setVehiculo(vehiculo);

        servicio.setFechaServicio(dto.getFechaServicio());
        servicio.setHoraInicio(dto.getHoraInicio());
        servicio.setHoraFin(dto.getHoraFin());
        servicio.setEstado(dto.getEstado());
        servicio.setCosto(dto.getCosto());
        servicio.setObservaciones(dto.getObservaciones());

        Servicio creado = servicioService.crearServicio(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTO> actualizar(@PathVariable Integer id,
                                                  @RequestBody ServicioDTO dto) {
        Servicio datos = new Servicio();
        datos.setFechaServicio(dto.getFechaServicio());
        datos.setHoraInicio(dto.getHoraInicio());
        datos.setHoraFin(dto.getHoraFin());
        datos.setEstado(dto.getEstado());
        datos.setCosto(dto.getCosto());
        datos.setObservaciones(dto.getObservaciones());

        Servicio actualizado = servicioService.actualizarServicio(id, datos);
        return ResponseEntity.ok(convertirADTO(actualizado));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ServicioDTO> actualizarEstado(@PathVariable Integer id,
                                                        @RequestParam String estado) {
        Servicio actualizado = servicioService.actualizarEstado(id, estado);
        return ResponseEntity.ok(convertirADTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        servicioService.eliminarServicio(id);
        return ResponseEntity.noContent().build();
    }

    private ServicioDTO convertirADTO(Servicio servicio) {
        return new ServicioDTO(
                servicio.getId(),
                servicio.getCliente().getId(),
                servicio.getCliente().getRazonSocial(),
                servicio.getCategoria().getId(),
                servicio.getCategoria().getNombre(),
                servicio.getRuta().getId(),
                servicio.getRuta().getOrigen() + " → " + servicio.getRuta().getDestino(),
                servicio.getConductor().getId(),
                servicio.getConductor().getNombres() + " " + servicio.getConductor().getApellidos(),
                servicio.getVehiculo().getId(),
                servicio.getVehiculo().getPlaca(),
                servicio.getFechaServicio(),
                servicio.getHoraInicio(),
                servicio.getHoraFin(),
                servicio.getEstado(),
                servicio.getCosto(),
                servicio.getObservaciones()
        );
    }
}