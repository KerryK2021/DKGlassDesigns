package com.coeproject.dkglassdesigns.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@AllArgsConstructor
@Getter
@Setter
public class OrderDetails {

    private int id;
    private double price;
    private int quantity;
    private int productId;
    private int orderId;

    public OrderDetails() {
    }

    public OrderDetails(Integer id, double price, Integer quantity, Integer productId, Integer orderId) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.productId = productId;
        this.orderId = orderId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "product_id", nullable = true)
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column(name = "order_id", nullable = false)
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetails [id=" + id + ", price=" + price + ", quantity=" + quantity + ", product_id=" + productId + ", order_id=" + orderId + "]";
    }
}
