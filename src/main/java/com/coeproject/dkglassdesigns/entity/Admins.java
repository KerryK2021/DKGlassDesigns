package com.coeproject.dkglassdesigns.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admins")
@AllArgsConstructor
@Getter
@Setter
public class Admins {

    private int id;
    private int userId;

    public Admins() {
    }

    public Admins(Integer userId) {
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public Integer getuserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Admins [id=" + id + ", userId=" + userId + "]";
    }
}
