package edu.upc.anse.backendanse.controller;

import edu.upc.anse.backendanse.dto.ClienteDTO;
import edu.upc.anse.backendanse.entity.Cliente;
import edu.upc.anse.backendanse.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        List<Cliente> clientes = clienteService.listarTodos();
        List<ClienteDTO> dtos = clientes.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(convertirADTO(cliente));
    }

    @GetMapping("/ruc/{rucDni}")
    public ResponseEntity<ClienteDTO> buscarPorRucDni(@PathVariable String rucDni) {
        Cliente cliente = clienteService.buscarPorRucDni(rucDni);
        return ResponseEntity.ok(convertirADTO(cliente));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteDTO>> buscarPorRazonSocial(@RequestParam String razonSocial) {
        List<Cliente> clientes = clienteService.buscarPorRazonSocial(razonSocial);
        List<ClienteDTO> dtos = clientes.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@RequestBody ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setRazonSocial(dto.getRazonSocial());
        cliente.setRucDni(dto.getRucDni());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());
        cliente.setDireccion(dto.getDireccion());

        Cliente creado = clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Integer id,
                                                 @RequestBody ClienteDTO dto) {
        Cliente datos = new Cliente();
        datos.setRazonSocial(dto.getRazonSocial());
        datos.setTelefono(dto.getTelefono());
        datos.setCorreo(dto.getCorreo());
        datos.setDireccion(dto.getDireccion());

        Cliente actualizado = clienteService.actualizarCliente(id, datos);
        return ResponseEntity.ok(convertirADTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    private ClienteDTO convertirADTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getRazonSocial(),
                cliente.getRucDni(),
                cliente.getTelefono(),
                cliente.getCorreo(),
                cliente.getDireccion()
        );
    }
}