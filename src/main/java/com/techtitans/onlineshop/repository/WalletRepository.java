package com.techtitans.onlineshop.repository;

import com.techtitans.onlineshop.domain.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> getWalletById(UUID walletId);
    Page<Wallet> findAll(Pageable pageable);

    Optional<Wallet> findWalletById(UUID walletId);

    Optional<Wallet>  findWalletByCustomerId(UUID uuid);

}
