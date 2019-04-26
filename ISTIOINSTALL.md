# Istio installation step

###Step 1:  Configure minikube to setup istio

```bash
$ sudo minikube config set cpus 4
$ sudo minikube config set memory 8192
$ sudo minikube config set disk-size 50g
$ sudo minikube addons enable ingress
$ sudo minikube start
```

###Step 2: To download Istio, run this command

```bash
$ curl -L https://git.io/getLatestIstio | sh -
```

###Step 3: To install Istio, run these commands:

```bash
$ cd istio-1.1.3
$ for i in install/kubernetes/helm/istio-init/files/crd*yaml; do kubectl apply -f $i; done
$ kubectl apply -f install/kubernetes/istio-demo.yaml
```

###Step 4: Verifying the installation

```bash
$ kubectl get pods -n istio-system
```

###Step 5: Enable automatic sidecar injection

```bash
$ kubectl label namespace default istio-injection=enabled
```

###Step 6: To verify the istio ingress gateway service

```bash
$ kubectl get svc istio-ingressgateway -n istio-system
```

#To access the services exposed in istio service

```bash
$ export INGRESS_HOST=$(sudo minikube ip)
$ export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')

$ export GATEWAY=$INGRESS_HOST:$INGRESS_PORT
```

