package com.fleetpay.statement.api;

import com.fleetpay.statement.domain.LedgerEntry;
import com.fleetpay.statement.repo.LedgerRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/statements")
public class StatementController {
    private final LedgerRepository repo;
    public StatementController(LedgerRepository repo) { this.repo = repo; }

    @GetMapping("/{accountId}/{month}")
    @Operation(summary="Fetch a synthetic monthly statement for an account (demo).")
    public ResponseEntity<List<LedgerEntry>> byMonth(@PathVariable String accountId, @PathVariable int month) {
        // Demo only: return all ledger entries (a real impl would query by partition key + month)
        return ResponseEntity.ok(repo.findAll());
    }
}
