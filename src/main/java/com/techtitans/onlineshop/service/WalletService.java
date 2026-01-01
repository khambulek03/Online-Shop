package com.techtitans.onlineshop.service;

import com.techtitans.onlineshop.domain.Wallet;
import com.techtitans.onlineshop.dto.WalletDataRequest;
import com.techtitans.onlineshop.exception.custom.NotFoundException;
import com.techtitans.onlineshop.exception.custom.ObjectAlreadyExistException;
import com.techtitans.onlineshop.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    private final WalletRepository walletRepository;
    private final CustomerService customerService;

    public WalletService(WalletRepository walletRepository, CustomerService customerService) {
        this.walletRepository = walletRepository;
        this.customerService = customerService;
    }

    public Wallet getWallet(UUID walletId) {
        return walletRepository.findWalletByCustomerId(walletId).
                orElseThrow(() -> new NotFoundException("Your wallet was not found"));

    }

    public Page<Wallet> wallets(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        return walletRepository.findAll(pageable);
    }

    public Wallet createWallet(WalletDataRequest walletDataRequest) {

        if (!customerService.customer(walletDataRequest.customerId())) {
            logger.info("Customer was not found on database, please register");
            throw new NotFoundException("Customer does not exist, please make sure you are registered");
        }

        if (walletRepository.findWalletByCustomerId(walletDataRequest.customerId()) != null) {
            logger.info("Customer already have a wallet");
            throw new ObjectAlreadyExistException("You already have a wallet");
        }

        Wallet wallet = new Wallet();
        wallet.setBalance(0.0);
        wallet.setCustomerId(walletDataRequest.customerId());

        logger.info("Wallet has been created completely");
        return walletRepository.save(wallet);
    }

    public Wallet rechargeWallet(UUID walletId, Double balance) {
        Wallet wallet = walletRepository.findWalletById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet was not found"));

        wallet.setBalance(wallet.getBalance() + balance);
        logger.info("Recharging successful, your balance is now: R{}", wallet.getBalance());
        return walletRepository.save(wallet);
    }

    public Wallet purchaseFromWallet(UUID walletId, Double amount) {
        Wallet wallet = walletRepository.findWalletById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet was not found"));

        wallet.setBalance(wallet.getBalance() - amount);
        logger.info("Balance is now: R{}", wallet.getBalance());
        return walletRepository.save(wallet);
    }
}