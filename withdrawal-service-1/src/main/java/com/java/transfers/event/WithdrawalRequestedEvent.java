package com.java.transfers.event;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawalRequestedEvent {
	private String senderId;
    private String recepientId;
    private BigDecimal amount;
}