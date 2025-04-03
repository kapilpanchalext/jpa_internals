package com.java.transfers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.transfers.model.TransferEntity;

public interface TransferRepository extends JpaRepository<TransferEntity, String>{

}