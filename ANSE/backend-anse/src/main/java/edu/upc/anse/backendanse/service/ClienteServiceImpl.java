package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Cliente;
import edu.upc.anse.backendanse.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByRucDni(cliente.getRucDni())) {
            throw new RuntimeException("Ya existe un cliente con RUC/DNI: " + cliente.getRucDni());
        }
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente obtenerClientePorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarPorRazonSocial(String razonSocial) {
        return clienteRepository.findByRazonSocialContainingIgnoreCase(razonSocial);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorRucDni(String rucDni) {
        return clienteRepository.findByRucDni(rucDni)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con RUC/DNI: " + rucDni));
    }

    @Override
    public Cliente actualizarCliente(Integer id, Cliente clienteActualizado) {
        Cliente cliente = obtenerClientePorId(id);
        cliente.setRazonSocial(clienteActualizado.getRazonSocial());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setCorreo(clienteActualizado.getCorreo());
        cliente.setDireccion(clienteActualizado.getDireccion());
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Integer id) {
        Cliente cliente = obtenerClientePorId(id);
        clienteRepository.delete(cliente);
    }

    @Override
    public boolean existeClientePorRucDni(String rucDni) {
        return clienteRepository.existsByRucDni(rucDni);
    }
}