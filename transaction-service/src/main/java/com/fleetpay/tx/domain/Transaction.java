package com.fleetpay.tx.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private String cardNumber;

    @Column(nullable=false)
    private Long amountCents;

    @Column(nullable=false)
    private String status; // AUTHORIZED/CAPTURED/VOIDED/REFUNDED

    @Column(nullable=false)
    private Instant createdAt = Instant.now();

    public UUID getId() { return id; }
    public String getCardNumber() { return cardNumber; }
    public Long getAmountCents() { return amountCents; }
    public String getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }

    public void setCardNumber(String n) { this.cardNumber = n; }
    public void setAmountCents(Long a) { this.amountCents = a; }
    public void setStatus(String s) { this.status = s; }
}
