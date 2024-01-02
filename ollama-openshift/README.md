# Ollama on OpenShift

## Deploy Ollama

```bash
oc create -f ollama-deployment.yaml
```

## Get the route

```bash
export OLLAMA_HOST=`oc get route/ollama  --template='{{.spec.host}}'`
```

## Pull the model

```bash
curl ${OLLAMA_HOST}/api/pull -d '{
"name": "llama2"  
}'
```

## Consume the model

```bash
curl -X POST ${OLLAMA_HOST}/api/generate -d '{
  "model": "llama2",
  "prompt":"Why is the sky blue?"
}'
```