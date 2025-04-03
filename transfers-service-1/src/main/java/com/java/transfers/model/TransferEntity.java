package com.java.transfers.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="transfers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferEntity implements Serializable{
	private static final long serialVersionUID = 3092003493407823513L;

	@Id
    @Column(nullable = false)
    private String transferId;

    @Column(nullable = false)
    private String senderId;

    @Column(nullable = false)
    private String recepientId;

    @Column(nullable = false)
    private BigDecimal amount;
}