package com.web.supermarket.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.supermarket.dto.ProductoRequestDTO;
import com.web.supermarket.entity.Producto;
import com.web.supermarket.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@Valid @RequestBody ProductoRequestDTO dto) {
        Producto nuevoProducto = productoService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    // 2. GET: Listar todos los productos (Retorna 200 OK)
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        List<Producto> productos = productoService.listarTodos();
        return ResponseEntity.ok(productos);
    }

    // 3. GET: Consultar un producto por ID (Retorna 200 OK o 404 Not Found)
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        Producto producto = productoService.buscarPorId(id);
        return ResponseEntity.ok(producto);
    }

    // 4. PUT: Actualizar un producto existente (Retorna 200 OK)
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto) {
        Producto productoActualizado = productoService.actualizar(id, dto);
        return ResponseEntity.ok(productoActualizado);
    }

    // 5. DELETE: Eliminar un producto (Retorna 204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // --- ENDPOINTS DE CONSULTAS ADICIONALES (Punto 5.2) ---

    // GET /api/productos/categoria/{categoriaId}
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> listarPorCategoria(@PathVariable Long categoriaId) {
        List<Producto> productos = productoService.listarPorCategoria(categoriaId);
        return ResponseEntity.ok(productos);
    }

    // GET /api/productos/buscar?nombre=arroz
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    // GET /api/productos/stock-bajo?limite=10
    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Producto>> listarStockBajo(@RequestParam Integer limite) {
        List<Producto> productos = productoService.listarStockBajo(limite);
        return ResponseEntity.ok(productos);
    }
}