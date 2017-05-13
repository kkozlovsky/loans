# Тестовое задание
Используемые технологии:
* Backend:
    - [Spring Boot](https://projects.spring.io/spring-boot/)
    - [Spring Data JPA](http://projects.spring.io/spring-data-jpa/)
    - [H2 Database](http://www.h2database.com/html/main.html)
    - [Gradle](https://gradle.org/)
    - [Project Lombok](https://projectlombok.org/)
    - [JUnit](http://junit.org/junit4/)
    - [Swagger](http://swagger.io/)
* Frontend  
    - [Angular 4](https://angular.io/)
    - [bootstrap 4](https://v4-alpha.getbootstrap.com/)
    - [Font Awesome](http://fontawesome.io/)
    - [npm](https://www.npmjs.com/)

### Сборка
 ```sh
./gradlew clean build
 ```
### Запуск
##### 1) Backend
```sh
ru.kerporation.Application -> main
```
##### 2) Frontend
```sh
cd frontend
npm start
``````
##### 3) Profit
```sh
http://localhost:4200/
```
##### PS: Есть поддержка Swagger
```sh
http://localhost:8080/swagger-ui/index.html
```

### Описание REST API 
#### Работа с заявками
* Получить список займов
```sh
http://localhost:8080/apiloans/loans GET
```

* Получить описание займа по id
```sh
http://localhost:8080/apiloans/loans/{loanId} GET
```

* Создать новую заявку на займ
```sh
http://localhost:8080/apiloans/users/{userId} POST
{
    "term": 60,
    "amount": 130
}
```

* Создание новой заявки будет отклонено, если `userId` есть в чёрном списке
```sh
http://localhost:8080/apiloans/users/fake1@test.ru POST
{
    "term": 60,
    "amount": 130
}
```

* Изменить существующую заявку на займ
```sh
http://localhost:8080/apiloans/loans/{loanId} PUT
{
    "term": 600,
    "amount": 1300
}
```

* Удалить существующую заявку
```sh
http://localhost:8080/apiloans/loans/{loanId} DELETE
```

* Получить займы конкретного пользователя
```sh
http://localhost:8080/apiloans/users/{userId} GET
```

#### Работа с пользователями
* Получить список заёмщиков
```sh
http://localhost:8080/apiusers/users GET
```

* Получить чёрный список заёмщиков
```sh
http://localhost:8080/apiusers/blacklist GET
```

* Получить описание заёмщика по `userId`
```sh
http://localhost:8080/apiusers/users/{userId} GET
```

* Создать нового заёмщика
```sh
http://localhost:8080/apiusers/users POST
{
  "userId":12,
  "name": "Имя",
  "surName": "Фамилия",
  "blacklist": false
}
```

* Изменить данные существующего заёмщика
```sh
http://localhost:8080/apiusers/users/{userId} PUT
{
  "name": "Имя",
  "surName": "Фамилия",
  "blacklist": true
}
```

* Удалить существующего заёмщика
```sh
http://localhost:8080/apiusers/users/{userId} DELETE
```

### ***Дополнительные условия:***
- Отклонять заявку, если она пришла от пользователя из чёрного списка
- Пользователь может создать не более одной заявки в секунду
- Необходимо определить код страны из которой пришёл запрос
- Возможность генерировать случайного пользователя
 
Для тестрирования API использовался [YARC!](http://yet-another-rest-client.com/)