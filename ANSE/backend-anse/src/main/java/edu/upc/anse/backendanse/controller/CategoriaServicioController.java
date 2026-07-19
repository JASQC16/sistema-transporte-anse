package edu.upc.anse.backendanse.controller;

import edu.upc.anse.backendanse.dto.CategoriaServicioDTO;
import edu.upc.anse.backendanse.entity.CategoriaServicio;
import edu.upc.anse.backendanse.service.CategoriaServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaServicioController {
    private final CategoriaServicioService categoriaService;

    @Autowired
    public CategoriaServicioController(CategoriaServicioService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaServicioDTO>> listarTodos() {
        List<CategoriaServicio> categorias = categoriaService.listarTodos();
        List<CategoriaServicioDTO> dtos = categorias.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaServicioDTO> obtenerPorId(@PathVariable Integer id) {
        CategoriaServicio categoria = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(convertirADTO(categoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaServicioDTO> crear(@RequestBody CategoriaServicioDTO dto) {
        CategoriaServicio categoria = new CategoriaServicio();
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());

        CategoriaServicio creado = categoriaService.crearCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirADTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaServicioDTO> actualizar(@PathVariable Integer id,
                                                           @RequestBody CategoriaServicioDTO dto) {
        CategoriaServicio datos = new CategoriaServicio();
        datos.setNombre(dto.getNombre());
        datos.setDescripcion(dto.getDescripcion());

        CategoriaServicio actualizado = categoriaService.actualizarCategoria(id, datos);
        return ResponseEntity.ok(convertirADTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    private CategoriaServicioDTO convertirADTO(CategoriaServicio categoria) {
        return new CategoriaServicioDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}