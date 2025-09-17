# カスタムバリデーションの動作確認

## API の起動

```sh
./mvnw spring-boot:run
```

## 四則演算 API

### 正常系の動作確認、足し算。

```sh
curl -X POST -H "Content-Type: application/json" localhost:8080/calc -d '{"left": 2, "right": 1, "operation": "ADD"}'
```

⇒ 計算結果が返却されることを確認。

### バリデーションエラーの動作確認、ゼロ除算。

```sh
curl -X POST -H "Content-Type: application/json" localhost:8080/calc -d '{"left": 2, "right": 0, "operation": "DIVIDE"}'
```

⇒ 500 Internal Server Error ではなく、 400 Bad Request が返ることを確認。


## バッテリー状態通知 API

### 正常系の動作確認 バッテリー残量 50%

```sh
curl -X POST -H "Content-Type: application/json" localhost:8080/update-battery-status -d '{"batteryLevel": 50, "charging": true, "health": "GOOD"}'
```

⇒ 「Battery status updated: BatteryStatus(batteryLevel=50, charging=true, health=GOOD)」


### 正常系の動作確認 バッテリー残量 不明

```sh
curl -X POST -H "Content-Type: application/json" localhost:8080/update-battery-status -d '{"batteryLevel": 255, "charging": true, "health": "GOOD"}'
```

⇒ 「Battery status updated: BatteryStatus(batteryLevel=255, charging=true, health=GOOD)」


### バリデーションエラーの動作確認 バッテリー残量不正

```sh
curl -X POST -H "Content-Type: application/json" localhost:8080/update-battery-status -d '{"batteryLevel": 150, "charging": true, "health": "GOOD"}'
```

⇒ 400 Bad Request が返ることを確認。
