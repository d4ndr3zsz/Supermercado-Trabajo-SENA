package com.web.supermarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.supermarket.dto.ProductoRequestDTO;
import com.web.supermarket.entity.Categoria;
import com.web.supermarket.entity.Producto;
import com.web.supermarket.entity.Proveedor;
import com.web.supermarket.exception.ResourceNotFoundException;
import com.web.supermarket.respository.CategoriaRepository;
import com.web.supermarket.respository.ProductoRepository;
import com.web.supermarket.respository.ProveedorRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, ProveedorRepository proveedorRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + id));
    }

    public Producto guardar(ProductoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + dto.getProveedorId()));

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCodigoBarras(dto.getCodigoBarras());
        producto.setCategoria(categoria);
        producto.setProveedor(proveedor);

        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, ProductoRequestDTO dto) {
        Producto productoExistente = buscarPorId(id);

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + dto.getProveedorId()));

        productoExistente.setNombre(dto.getNombre());
        productoExistente.setPrecio(dto.getPrecio());
        productoExistente.setStock(dto.getStock());
        productoExistente.setCodigoBarras(dto.getCodigoBarras());
        productoExistente.setCategoria(categoria);
        productoExistente.setProveedor(proveedor);

        return productoRepository.save(productoExistente);
    }

    public void eliminar(Long id) {
        Producto producto = buscarPorId(id);
        productoRepository.delete(producto);
    }


    public List<Producto> listarPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> listarStockBajo(Integer limite) {
        return productoRepository.findByStockLessThanEqual(limite);
    }
}