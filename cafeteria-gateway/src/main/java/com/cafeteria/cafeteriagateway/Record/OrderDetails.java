package com.cafeteria.cafeteriagateway.Record;

import java.util.UUID;

public class OrderDetails {

    private UUID cart_id = UUID.randomUUID();
    private UUID user_id;
    private String product_code;
    private Long quantity;

    public OrderDetails(UUID cart_id, UUID user_id, String product_code, Long quantity) {
        this.cart_id = cart_id;
        this.user_id = user_id;
        this.product_code = product_code;
        this.quantity = quantity;
    }

    public OrderDetails() {}

    public Long getQuantity() {
        return quantity;
    }

    public UUID getCart_id() {
        return cart_id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setCart_id(UUID cart_id) {
        this.cart_id = cart_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
