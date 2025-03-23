package com.fleetpay.statement.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("ledger")
public class LedgerEntry {
    @PrimaryKey
    private UUID id;
    private String accountId;
    private Long amountCents;
    private String type;
    private Instant ts;

    public UUID getId() { return id; }
    public String getAccountId() { return accountId; }
    public Long getAmountCents() { return amountCents; }
    public String getType() { return type; }
    public Instant getTs() { return ts; }

    public void setId(UUID id) { this.id = id; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public void setAmountCents(Long amountCents) { this.amountCents = amountCents; }
    public void setType(String type) { this.type = type; }
    public void setTs(Instant ts) { this.ts = ts; }
}
