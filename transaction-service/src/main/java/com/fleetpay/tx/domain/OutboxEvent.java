package com.fleetpay.tx.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox")
public class OutboxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private String aggregateType;

    @Column(nullable=false)
    private String eventType;

    @Column(nullable=false, columnDefinition = "text")
    private String payload;

    @Column(nullable=false)
    private Instant createdAt = Instant.now();

    public UUID getId() { return id; }
    public String getAggregateType() { return aggregateType; }
    public String getEventType() { return eventType; }
    public String getPayload() { return payload; }
    public Instant getCreatedAt() { return createdAt; }

    public void setAggregateType(String t) { this.aggregateType = t; }
    public void setEventType(String e) { this.eventType = e; }
    public void setPayload(String p) { this.payload = p; }
}
