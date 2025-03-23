package com.fleetpay.card.api;

import com.fleetpay.card.domain.Card;
import com.fleetpay.card.repo.CardRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardRepository repo;
    public CardController(CardRepository repo) { this.repo = repo; }

    public record CreateCardRequest(@NotBlank String number) {}
    @PostMapping
    @Transactional
    @Operation(summary = "Issue a new card")
    public ResponseEntity<Card> create(@RequestBody CreateCardRequest req) {
        var c = new Card();
        c.setNumber(req.number());
        c.setStatus("ACTIVE");
        return ResponseEntity.ok(repo.save(c));
    }

    @GetMapping
    public List<Card> list() { return repo.findAll(); }
}
