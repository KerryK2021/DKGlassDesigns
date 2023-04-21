package com.coeproject.dkglassdesigns.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products_supplier")
@AllArgsConstructor
@Getter
@Setter
public class ProductsSupplier {

    private int id;
    private int productId;
    private int supplierId;

    public ProductsSupplier() {
    }

    public ProductsSupplier(Integer productId, Integer supplierId) {
        this.productId = productId;
        this.supplierId = supplierId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "product_id", nullable = false)
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column(name = "supplier_id", nullable = false)
    public Integer getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "ProductsSupplier [id=" + id + ", product_id=" + productId + ", supplier_id=" + supplierId + "]";
    }
}
