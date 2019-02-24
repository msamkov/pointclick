# pointclick

## Запуск приложения
```
./gradlew clean
./gradlew build
docker-compose build
docker-compose up -d
```


## API

### Посещения сайта пользователем
POST localhost:8081/pointclick/api/v1/pageclick/  

Запрос
```
{
  "userId" : 1001,
  "pageId" : 2001
}
```

Ответ
```
{
  "pageClicks" : 2233,
  "usersUniq"  : 12
}
```

### Получение статистики посещения за произвольный период
GET localhost:8081/pointclick/api/v1/pageclick/statistics

Запрос
```
localhost:8081/pointclick/api/v1/pageclick/statistics?dateStart=2019-02-02T00%3A00%3A00.000%2B03%3A00&dateEnd=2019-03-04T00%3A00%3A00.000%2B03%3A00
```

Ответ
```
{
  "pageClicks" : 2233,
  "usersUniq"  : 12,
  "usersTop" : 22
}
```