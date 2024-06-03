package com.grupo5;

public class ItemCarrito {

    private Product product;
    private int quantity;

    public ItemCarrito(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Cantidad debe ser mayor que 0");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Cantidad debe ser mayor que 0");
        }
        this.quantity = quantity;
    }
}
