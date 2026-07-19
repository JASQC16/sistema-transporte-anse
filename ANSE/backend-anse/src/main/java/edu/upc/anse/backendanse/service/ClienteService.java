package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    Cliente obtenerClientePorId(Integer id);
    List<Cliente> listarTodos();
    List<Cliente> buscarPorRazonSocial(String razonSocial);
    Cliente buscarPorRucDni(String rucDni);
    Cliente actualizarCliente(Integer id, Cliente cliente);
    void eliminarCliente(Integer id);
    boolean existeClientePorRucDni(String rucDni);
}