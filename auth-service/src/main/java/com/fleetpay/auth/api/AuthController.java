package com.fleetpay.auth.api;

import com.fleetpay.platform.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public record TokenRequest(@NotBlank String username, @NotBlank String role) {}
    public record TokenResponse(String token) {}

    @PostMapping("/token")
    @Operation(summary="Issue a JWT for a user with a role claim (dev/demo only).")
    public ResponseEntity<TokenResponse> token(@RequestBody TokenRequest req) {
        var token = JwtUtil.issueToken(req.username(), Map.of("role", req.role()), 3600_000);
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
