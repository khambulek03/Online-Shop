package com.techtitans.onlineshop.dto;

public record ProductDataRequest(
        String name, String model, String cpu, Long ram, Double rom, Long quantity) {
}
