package com.techtitans.onlineshop.controller;

import com.techtitans.onlineshop.domain.Wallet;
import com.techtitans.onlineshop.dto.WalletDataRequest;
import com.techtitans.onlineshop.service.WalletService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api/shop/wallets")
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/")
    @ResponseStatus(OK)
    public Page<Wallet> getAllWallets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "balance") String sort) {
        return walletService.wallets(page, size, sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> wallet(@PathVariable UUID id) {
        Wallet wallet = walletService.getWallet(id);
        logger.info("Your wallet id is {}", wallet.getId());
        return ResponseEntity.status(OK).body(wallet);
    }

    @PostMapping("/create")
    public ResponseEntity<Wallet> wallet(@Valid @RequestBody WalletDataRequest dataRequest) {
        Wallet wallet = walletService.createWallet(dataRequest);
        return ResponseEntity.status(CREATED).body(wallet);
    }
}
