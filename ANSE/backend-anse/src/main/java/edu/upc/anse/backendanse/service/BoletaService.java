package edu.upc.anse.backendanse.service;

import edu.upc.anse.backendanse.entity.Boleta;
import java.time.LocalDate;
import java.util.List;

public interface BoletaService {
    Boleta crearBoleta(Boleta boleta);
    Boleta obtenerBoletaPorId(Integer id);
    List<Boleta> listarTodos();
    Boleta obtenerBoletaPorServicio(Integer servicioId);
    Boleta obtenerBoletaPorNumero(String numero);
    List<Boleta> buscarPorRangoFechas(LocalDate inicio, LocalDate fin);
    Boleta actualizarBoleta(Integer id, Boleta boleta);
    void eliminarBoleta(Integer id);
    boolean existeBoletaPorNumero(String numero);
}