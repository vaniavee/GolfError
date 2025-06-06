package com.kidaro.kael.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidaro.kael.model.RestTransaction;

public interface RestTransactionRepository extends JpaRepository<RestTransaction, Long> {}
