package com.universidad.apiproductos.service;

import com.universidad.apiproductos.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class ProductoService {


    private final List<Producto> productos = new ArrayList<>();


    private final AtomicLong contadorId = new AtomicLong(1);


    public ProductoService() {
        productos.add(new Producto(contadorId.getAndIncrement(), "Laptop",
                "Laptop 15 pulgadas Intel Core i7", 1299.99));
        productos.add(new Producto(contadorId.getAndIncrement(), "Mouse",
                "Mouse inalámbrico ergonómico", 29.99));
        productos.add(new Producto(contadorId.getAndIncrement(), "Teclado",
                "Teclado mecánico retroiluminado", 89.99));
    }


    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos);
    }


    public Optional<Producto> buscarPorId(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }


    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {

            producto.setId(contadorId.getAndIncrement());
            productos.add(producto);
        } else {

            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getId().equals(producto.getId())) {
                    productos.set(i, producto);
                    break;
                }
            }
        }
        return producto;
    }

    public void eliminar(Long id) {
        boolean eliminado = productos.removeIf(p -> p.getId().equals(id));
        if (!eliminado) {
            throw new RuntimeException("Producto no encontrado: " + id);
        }
    }
}
