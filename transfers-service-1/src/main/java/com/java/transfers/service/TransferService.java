package com.java.transfers.service;

import com.java.transfers.model.TransferRestModel;

public interface TransferService {
	public boolean transfer(TransferRestModel productPaymentRestModel);
}