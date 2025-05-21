# 📘 Reservation API 명세서


## ✅ 1. GET `/reservations`

- **설명**: 모든 예약 정보를 조회
- **메서드**: `GET`
- **응답**:
    - `200 OK`
    - `Content-Type`: `application/json`

### 🔸 응답 예시

```json
[
  {
    "id": 1,
    "name": "브라운",
    "date": "2023-01-01",
    "time": "10:00"
  },
  {
    "id": 2,
    "name": "브라운",
    "date": "2023-01-02",
    "time": "11:00"
  }
]
```

---

## ✅ 2. POST `/reservations`

- **설명**: 예약 추가
- **메서드**: `POST`
- **요청 본문**:
    - `Content-Type`: `application/json`

### 🔸 요청 예시

```json
{
  "date": "2023-08-05",
  "name": "브라운",
  "time": "15:40"
}
```

- **응답**:
    - `201 Created`
    - `Location`: `/reservations/{id}`
    - `Content-Type`: `application/json`

### 🔸 응답 예시

```json
{
  "id": 1,
  "name": "브라운",
  "date": "2023-08-05",
  "time": "15:40"
}
```

- **예외 응답**:
    - `400 Bad Request`: 필드가 비어 있을 경우
    - 메시지 예시:
      ```json
      {
        "message": "{누락된 데이터}가 입력되지 않았습니다."
      }
      ```

---

## ✅ 3. DELETE `/reservations/{id}`

- **설명**: ID에 해당하는 예약을 삭제
- **메서드**: `DELETE`
- **경로 변수**:
    - `id`: 삭제할 예약의 ID (Long)

- **응답**:
    - `204 No Content`

- **예외 응답**:
    - `400 Bad Request`: 해당 ID의 예약이 존재하지 않음
    - 메시지 예시:
      ```json
      {
        "message": "ID 99에 해당하는 예약이 존재하지 않습니다."
      }
      ```

---

## 📌 예외

| 예외 상황 | HTTP 상태 코드 | 메시지 예시 |
|-----------|----------------|-------------|
| 필드 누락  | `400 Bad Request` | `"예약자 이름, 날짜, 시간이 비어 있을 수 없습니다."` |
| 예약 없음 | `400 Bad Request`    | `"ID 99에 해당하는 예약이 존재하지 않습니다."` |

---

# 📘 Time API 명세서

## 1. GET `/times`

- 설명: 모든 시간 정보를 조회
- 메서드: GET
- 응답:
  - 200 OK
  - Content-Type: application/json

### 응답 예시

```json
[
  {
    "id": 1,
    "time": "10:00"
  },
  {
    "id": 2,
    "time": "11:00"
  }
]
```

---

## 2. POST `/times`

- 설명: 시간 추가
- 메서드: POST
- 요청 본문:
  - Content-Type: application/json

### 요청 예시

```json
{
  "time": "10:00"
}
```

- 응답:
  - 201 Created
  - Location: /times/{id}
  - Content-Type: application/json

### 응답 예시

```json
{
  "id": 4,
  "time": "10:00"
}
```

- 예외 응답:
  - 400 Bad Request: 시간이 비어 있을 경우
  - 메시지 예시:
  ```json
  {
    "message": "시간이 비어 있을 수 없습니다."
  }
  ```

---

## 3. DELETE `/times/{id}`

- 설명: ID에 해당하는 시간 정보를 삭제
- 메서드: DELETE
- 경로 변수:
  - id: 삭제할 시간의 ID (Long)

- 응답:
  - 204 No Content

- 예외 응답:
  - 400 Bad Request: 해당 ID의 시간이 존재하지 않을 경우
  - 메시지 예시:
  ```json
  {
    "message": "ID 99에 해당하는 시간이 존재하지 않습니다."
  }
  ```
