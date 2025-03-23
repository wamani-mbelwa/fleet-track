# FleetPay Microservices Suite

A compact, production-style fintech prototype: card management, auth, transaction authorization with Outbox events, and a ledger-backed statement service.

## Quickstart (local)
```bash
# 1) Build all services
./gradlew clean build

# 2) Bring up infra + services
docker compose -f deploy/compose/docker-compose.yml up -d

# 3) Get a token
curl -s -X POST http://localhost:8088/auth/token -H 'Content-Type: application/json'   -d '{ "username":"alice", "role":"admin" }'

# 4) Create a card
curl -s -X POST http://localhost:8088/cards -H 'Content-Type: application/json'   -d '{ "number":"4111111111111111" }'

# 5) Authorize a transaction
curl -s -X POST http://localhost:8088/transactions/authorize -H 'Content-Type: application/json'   -d '{ "cardNumber":"4111111111111111", "amountCents": 5000, "idempotencyKey":"abc-123" }'

# 6) Get a statement (demo)
curl -s http://localhost:8088/statements/acct-1/9 | jq .

# 7) Prometheus & Grafana
open http://localhost:9090
open http://localhost:3000 (admin/admin)
```
