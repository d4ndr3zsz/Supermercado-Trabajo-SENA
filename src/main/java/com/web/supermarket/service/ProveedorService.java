package com.web.supermarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.supermarket.entity.Proveedor;
import com.web.supermarket.exception.ResourceNotFoundException;
import com.web.supermarket.respository.ProveedorRepository;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    public Proveedor buscarPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con el ID: " + id));
    }

    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void eliminar(Long id) {
        Proveedor proveedor = buscarPorId(id);

        if (proveedor.getProductos() != null && !proveedor.getProductos().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar el proveedor porque tiene productos asociados.");
        }

        proveedorRepository.delete(proveedor);
    }
}