package com.privat.payment.paymentdaoservice.service;

import com.privat.payment.paymentdaoservice.controller.dto.TransferDTO;
import com.privat.payment.paymentdaoservice.dto.WrapperListResponse;
import com.privat.payment.paymentdaoservice.entities.Transfer;

import java.util.List;

public interface TransferService {
    void save(TransferDTO transfer);
    TransferDTO update(TransferDTO transfer);
    void delete(String id);
    TransferDTO findById(String id);
    WrapperListResponse<TransferDTO> findAllByPaymentInstructionId(String id);
}
