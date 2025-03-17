package com.flynn.tax_application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tax_client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nip", nullable = false)
    private String nip;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "mail", nullable = false)
    private String mail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "taxClient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Settlement> settlementList;
}
