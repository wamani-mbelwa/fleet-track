# kind deployment (starter)

```bash
kind create cluster --name fleetpay
kubectl create namespace fleetpay
kubectl apply -f gateway.yaml -n fleetpay
# Add manifests for each service similarly, then port-forward or use an ingress.
```
