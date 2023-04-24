package com.coeproject.dkglassdesigns.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@Getter
@Setter
public class Order {

    private int id;
    private Date orderDate;
    private String status;
    private String notes;
    private int customerId;

    public Order() {
    }

    public Order(Integer id, Date orderDate, String status, String notes, Integer customerId) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.notes = notes;
        this.customerId = customerId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "order_date", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Column(name = "status", nullable = false)
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "notes", nullable = true)
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "customer_id", nullable = false)
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", orderDate=" + orderDate + ", status=" + status + ", notes=" + notes + ", customerId=" + customerId + "]";
    }
}
