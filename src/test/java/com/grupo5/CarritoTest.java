package com.grupo5;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarritoTest extends TestCase {

    // Prueba para validar la creación de un producto con datos válidos
    @Test
    public void testCrearProducto() {
        Product product = new Product(1, "Producto 1", 499.99);
        assertEquals(1, product.getId());
        assertEquals("Producto 1", product.getName());
        assertEquals(499.99, product.getPrice());
    }

    // Prueba para validar que no se puede crear un producto con precio negativo
    @Test
    public void testCrearProductoPrecioNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "Producto 1", -100));
    }

    // Prueba para validar que no se puede establecer un precio negativo a un producto
    @Test
    public void testSetPrecioNegativoProducto() {
        Product product = new Product(1, "Producto 1", 499.99);
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(-500));
    }

    // Prueba para validar que se puede agregar un producto al carrito
    @Test
    public void testAgregarProducto() {
        Carrito carrito = new Carrito();
        Product product = new Product(1, "Producto 1", 499.99);

        carrito.agregarProducto(product, 1);
        assertEquals(1, carrito.getItems().size());
        assertEquals(product, carrito.getItems().get(0).getProduct());
        assertEquals(1, carrito.getItems().get(0).getQuantity());
    }

    // Prueba para validar que no se puede agregar un producto duplicado al carrito, sino que se actualiza la cantidad.
    @Test
    public void testAgregarProductoDuplicado() {
        Carrito carrito = new Carrito();
        Product product = new Product(1, "Producto 1", 499.99);
        carrito.agregarProducto(product, 1);
        carrito.agregarProducto(product, 2);

        assertEquals(1, carrito.getItems().size());
        assertEquals(product, carrito.getItems().get(0).getProduct());
        assertEquals(3, carrito.getItems().get(0).getQuantity());
    }

    // Prueba para validar que no se puede agregar un producto con una cantidad menor o igual a cero al carrito
    @Test
    public void testAgregarProductoCantidadInvalida() {
        Carrito carrito = new Carrito();
        Product product = new Product(1, "Producto 1", 499.99);

        assertThrows(IllegalArgumentException.class, () -> carrito.agregarProducto(product, -2));

    }

    // Prueba para validar que se puede eliminar producto del carrito
    @Test
    public void testEliminarProducto() {
        Carrito carrito = new Carrito();
        Product product = new Product(1, "Producto 1", 499.99);

        carrito.agregarProducto(product, 1);
        carrito.eliminarProducto(product);
        assertEquals(0, carrito.getItems().size());
    }

    // Prueba para validar que se puede modificar la cantidad de un producto en el carrito
    @Test
    public void testModificarProducto() {
        Carrito carrito = new Carrito();
        Product product = new Product(1, "Producto 1", 499.99);
        Product product2 = new Product(2, "Producto 2", 99.99);

        carrito.agregarProducto(product, 1);
        carrito.agregarProducto(product2, 4);
        carrito.modificarProducto(product, 2);

        assertEquals(2, carrito.getItems().get(0).getQuantity());
        assertNotEquals(2, carrito.getItems().get(1).getQuantity());
    }

    // Prueba para validar que no se puede establecer una cantidad menor o igual a cero para un producto en el carrito
    @Test
    public void testModificarProductoCantidadInvalida() {
        Carrito carrito = new Carrito();
        Product product = new Product(1, "Producto 1", 499.99);
        carrito.agregarProducto(product, 1);

        assertThrows(IllegalArgumentException.class, () -> carrito.modificarProducto(product, 0));
    }

    // Prueba para validar que se calcula correctamente el total del carrito
    @Test
    public void testCalcularTotalCarrito () {
        Carrito carrito = new Carrito();
        Product product = new Product(1, "Producto 1", 499.99);
        Product product2 = new Product(2, "Producto 2", 99.99);

        carrito.agregarProducto(product, 2);
        carrito.agregarProducto(product2, 1);
        double total = (product.getPrice() * 2) + product2.getPrice();
        assertEquals(total, carrito.calcularTotal());
    }
}
