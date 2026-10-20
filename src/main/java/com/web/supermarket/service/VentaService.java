package com.web.supermarket.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.supermarket.dto.DetalleVentaRequestDTO;
import com.web.supermarket.dto.VentaRequestDTO;
import com.web.supermarket.entity.Cliente;
import com.web.supermarket.entity.DetalleVenta;
import com.web.supermarket.entity.Producto;
import com.web.supermarket.entity.Venta;
import com.web.supermarket.exception.ResourceNotFoundException;
import com.web.supermarket.respository.ClienteRepository;
import com.web.supermarket.respository.ProductoRepository;
import com.web.supermarket.respository.VentaRepository;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;


    public VentaService(VentaRepository ventaRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    public List<Venta> listarTodas() {
        return ventaRepository.findAll();
    }

    public Venta buscarPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con el ID: " + id));
    }


    @Transactional
    public Venta registrarVenta(VentaRequestDTO dto) {
        

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + dto.getClienteId()));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFecha(LocalDateTime.now());

        List<DetalleVenta> detallesVenta = new ArrayList<>();
        double totalVenta = 0.0;

        for (DetalleVentaRequestDTO itemDto : dto.getDetalles()) {
            
            
        Producto producto = productoRepository.findById(itemDto.getProductoId())
            .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + itemDto.getProductoId()));

        if (producto.getStock() < itemDto.getCantidad()) {
             throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre() + " | Stock actual: " + producto.getStock() + " | Cantidad solicitada: " + itemDto.getCantidad());
            }

            producto.setStock(producto.getStock() - itemDto.getCantidad());
            productoRepository.save(producto);


            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(itemDto.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            double subtotal = itemDto.getCantidad() * producto.getPrecio();
            detalle.setSubtotal(subtotal);
            totalVenta += subtotal;
            detallesVenta.add(detalle);
        }

        venta.setDetalle_venta(detallesVenta);
        venta.setTotal(totalVenta);

        return ventaRepository.save(venta);
    }


    @Transactional
    public void eliminarVenta(Long id) {
        Venta venta = buscarPorId(id);
        for (DetalleVenta detalle : venta.getDetalle_venta()) {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() + detalle.getCantidad());
            productoRepository.save(producto);
        }

        ventaRepository.delete(venta);
    }
}