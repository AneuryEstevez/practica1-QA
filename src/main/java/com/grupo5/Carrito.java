package com.grupo5;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private List<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public void agregarProducto(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Cantidad debe ser mayor que 0");
        }
        for (ItemCarrito item : items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        ItemCarrito itemCarrito = new ItemCarrito(product, quantity);
        this.items.add(itemCarrito);
    }

    public void eliminarProducto(Product product) {
        this.items.removeIf(itemCarrito -> itemCarrito.getProduct().equals(product));
    }

    public void modificarProducto(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Cantidad debe ser mayor que 0");
        }
        for (ItemCarrito itemCarrito : this.items) {
            if (itemCarrito.getProduct().equals(product)) {
                itemCarrito.setQuantity(quantity);
            }
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito itemCarrito : this.items) {
            total += itemCarrito.getProduct().getPrice() * itemCarrito.getQuantity();
        }
        return total;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }
}
