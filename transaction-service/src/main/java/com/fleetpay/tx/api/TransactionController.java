package com.fleetpay.tx.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetpay.tx.domain.OutboxEvent;
import com.fleetpay.tx.domain.Transaction;
import com.fleetpay.tx.repo.OutboxRepository;
import com.fleetpay.tx.repo.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionRepository txRepo;
    private final OutboxRepository outbox;
    private final ObjectMapper om;

    public TransactionController(TransactionRepository txRepo, OutboxRepository outbox, ObjectMapper om) {
        this.txRepo = txRepo; this.outbox = outbox; this.om = om;
    }

    public record AuthRequest(@NotBlank String cardNumber, @Min(1) long amountCents, @NotBlank String idempotencyKey) {}
    public record TxResponse(String id, String status) {}

    @PostMapping("/authorize")
    @Transactional
    @Operation(summary="Authorize a transaction; records outbox event (Outbox pattern).")
    public ResponseEntity<TxResponse> authorize(@RequestBody AuthRequest req) throws JsonProcessingException {
        var tx = new Transaction();
        tx.setCardNumber(req.cardNumber());
        tx.setAmountCents(req.amountCents());
        tx.setStatus("AUTHORIZED");
        var saved = txRepo.save(tx);

        var evt = new OutboxEvent();
        evt.setAggregateType("Transaction");
        evt.setEventType("AUTHORIZED");
        evt.setPayload(om.writeValueAsString(new TxResponse(saved.getId().toString(), saved.getStatus())));
        outbox.save(evt);

        return ResponseEntity.ok(new TxResponse(saved.getId().toString(), saved.getStatus()));
    }
}
