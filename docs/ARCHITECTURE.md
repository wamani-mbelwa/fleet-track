# Architecture

- **Gateway**: Spring Cloud Gateway routes to internal services; can host rate limits and auth.
- **Auth**: Issues JWTs (dev: HS256). In prod, use asymmetric keys + rotation.
- **Card**: Postgres-backed CRUD for cards.
- **Transaction**: Authorize endpoint writes to Postgres and emits an Outbox record. A real deploy would publish Outbox rows to Kafka on a schedule.
- **Statement**: Cassandra-backed ledger + simple monthly statement read.

Patterns used:
- Clean Architecture (domain, repo interfaces, adapters)
- Outbox pattern (DDIA-aligned) to ensure reliable publication
- Resilience4j (add CB/retry per integration)
- Metrics with Micrometer/Prometheus and dashboards in Grafana
