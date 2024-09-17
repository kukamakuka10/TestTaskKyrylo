package com.privat.payment.paymentdaoservice.controller;

import com.privat.payment.paymentdaoservice.controller.dto.TransferDTO;
import com.privat.payment.paymentdaoservice.dto.WrapperListResponse;
import com.privat.payment.paymentdaoservice.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payment/transfer")
@AllArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    public HttpStatus save(@RequestBody TransferDTO transfer) {
        transferService.save(transfer);
        return HttpStatus.CREATED;
    }

    @PutMapping
    public ResponseEntity<TransferDTO> update(@RequestBody TransferDTO transferDTO) {

        TransferDTO updatedTransfer = transferService.update(transferDTO);
        return ResponseEntity.ok(updatedTransfer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        transferService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferDTO> findById(@PathVariable("id") String id) {
        TransferDTO transferDTO = transferService.findById(id);
        return ResponseEntity.ok(transferDTO);
    }

    @GetMapping
    public ResponseEntity<WrapperListResponse<TransferDTO>> findAllByPaymentInstructionId(
            @RequestParam("paymentInstructionId") String paymentInstructionId) {

        WrapperListResponse<TransferDTO> transfers = transferService.findAllByPaymentInstructionId(paymentInstructionId);
        return ResponseEntity.ok(transfers);
    }
}
