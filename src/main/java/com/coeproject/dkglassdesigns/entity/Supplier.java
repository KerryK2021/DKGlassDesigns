package com.coeproject.dkglassdesigns.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplier")
@AllArgsConstructor
@Getter
@Setter
public class Supplier {

    private int id;
    private String companyName;
    private String website;
    private String phone;
    private String email;
    private String notes;

    public Supplier() {
    }

    public Supplier(String companyName, String website, String phone, String email, String notes) {
        this.companyName = companyName;
        this.website = website;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "company_name", nullable = false)
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "website", nullable = false)
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }

    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "notes", nullable = true)
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Supplier [id=" + id + ", companyName=" + companyName + ", website=" + website + ", phone=" + phone + ", email=" + email + ", notes=" + notes + "]";
    }
}
