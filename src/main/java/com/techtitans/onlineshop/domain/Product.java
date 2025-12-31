package com.techtitans.onlineshop.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "wallet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    @Column(unique = true, nullable = false, updatable = false)
    private String model;
    private String cpu;
    private Long ram;
    private Double rom;

    private Long quantity;

    private UUID categoryId;

    private LocalDateTime createdAt;
    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
