# TensorFlow Demo

This demo shows how to use TensorFlow to create and serve a Machine Learning model.

```bash
docker run -p 8501:8501 --name=tf_serving --mount type=bind,source=./model,target=/models/model -e MODEL_NAME=model -t tensorflow/serving
```

```bash
curl -d '{"instances": [[10.0]]}' \                                                                  
    -H "Content-Type: application/json" \
    -X POST http://localhost:8501/v1/models/model:predict
```
