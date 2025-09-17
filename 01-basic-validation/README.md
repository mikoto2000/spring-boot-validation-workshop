# 基本バリデーションの動作確認

## API の起動

```sh
./mvnw spring-boot:run
```

## 数値エコー API

リクエストパラメーターひとつひとつに対してバリデーションをかける例。

### 正常系の動作確認

```sh
curl localhost:8080/number-echo?message=123
```

⇒ `123` が返却されることを確認。


### バリデーションエラーの動作確認

```sh
curl localhost:8080/number-echo?message=123a
```

⇒ 400 Bad Request が返ることを確認。


## Bean エコー API

リクエストボディ全体に対してバリデーションをかける例。

### 正常系の動作確認

```sh
curl -H 'Content-Type: application/json' localhost:8080/bean-echo -d '{"stringValue": "aaa", "integerValue": 123, "doubleValue": 33.3}'
```

⇒ `123` が返却されることを確認。


### バリデーションエラーの動作確認

```sh
curl -H 'Content-Type: application/json' localhost:8080/bean-echo -d '{"stringValue": null, "integerValue": 123, "doubleValue": 33.3}'
```

⇒ 400 Bad Request が返ることを確認。


