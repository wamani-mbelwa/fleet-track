package com.fleetpay.card.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable=false, unique=true)
    private String number;

    @Column(nullable=false)
    private String status; // ACTIVE/BLOCKED

    @Column(nullable=false)
    private Instant createdAt = Instant.now();

    public UUID getId() { return id; }
    public String getNumber() { return number; }
    public String getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }

    public void setNumber(String number) { this.number = number; }
    public void setStatus(String status) { this.status = status; }
}
