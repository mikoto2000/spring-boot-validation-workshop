# バリデーションエラーのハンドリングの動作確認

## API の起動

```sh
./mvnw spring-boot:run
```

## エコー API

```sh
curl localhost:8080/echo?message=123a
```

## 四則演算 API

### バリデーションエラーの動作確認、ゼロ除算。

```sh
curl -X POST -H "Content-Type: application/json" localhost:8080/calc -d '{"left": 2, "right": 0, "operation": "DIVIDE"}'
```
