# RUNBOOK

## Health & Probes
- Each service exposes `/actuator/health` and Prometheus metrics at `/actuator/prometheus`.

## SLOs (starter)
- Availability: 99.5% monthly
- Latency: p95 < 250ms for read APIs, p95 < 400ms for write APIs

## Alerts (starter)
- High error rate (>2% for 5m)
- Kafka consumer lag > 1000 for 10m
- DB connection pool saturation > 0.9 for 10m

## Incident Steps
1. Check Grafana: latency, error rate, JVM, DB pool.
2. Inspect logs with correlation id (if present).
3. If DB saturation, scale replicas or raise pool; examine slow queries.
4. If Kafka lag, scale consumers or increase partitions.
5. For hotfixes, use rolling restart to avoid downtime.
