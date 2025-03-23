package com.fleetpay.statement.repo;

import com.fleetpay.statement.domain.LedgerEntry;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface LedgerRepository extends CassandraRepository<LedgerEntry, UUID> {}
