# Grand Kapital

# Задание 
 https://docs.google.com/document/d/15mT8r_uVRpUYppJFPLpvjHaa8EZewAjC/edit?tab=t.0

## Сборка

Используется:
1. JDK 21.  
2. Postgre DB.
3. Spring cache
4. Spring security (jwt authentication/authorization без refresh token)
5. Spring schedule

Все настройки в application.yaml. Находятся в пакете resources.

Необходимо вызвать команду

```
mnv clean install
```

Запуск jar выглядит так

```
java -jar target/grandKapital-0.0.1.jar
```
Запускается на порту localhost:8080

## Объяснение решения и реализация

В сервисе реализована следующая функциональность:
Приложено [yaml описание](gen/openapi.yaml) - написано по openapi спецификации.
Сгенерировав openapi.yaml, можно увидеть шаблон по которому строилась REST endpoints(генерировать через mvn clean generate-sources)

## Детали реализации
1. Для инициализации и наполнения данными бд используется flyway
2. Кеширование осуществляется на запросе **GET /users/{userId}**, иные endpoint-ы кэшировать не имеет смысла
3. Автоматическое увеличение баланса клиента реализовано, через spring schedule
4. По инфраструктурному и бизнес коду используется логирование важных или критичных событий
