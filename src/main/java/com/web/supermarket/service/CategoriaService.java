package com.web.supermarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.supermarket.entity.Categoria;
import com.web.supermarket.exception.ResourceNotFoundException;
import com.web.supermarket.respository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con el ID: " + id));
    }

    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void eliminar(Long id) {
        Categoria categoria = buscarPorId(id);

        if (categoria.getProductos() != null && !categoria.getProductos().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar la categoría porque tiene productos asociados.");
        }

        categoriaRepository.delete(categoria);
    }
}
