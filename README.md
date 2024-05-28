# Тестовое задание

Создать Rest API сервис обработки запросов к одной таблице БД.
Сервис должен уметь добавлять новую запись в таблицу, обновлять и удалять запись в таблице по id этой записи.
Должна быть возможность получить одну запись по id.
Должны быть созданы тесты для данного сервиса:
- создание 100тыс новых записей в таблице
- выборка 1млн произвольных записей через 100 подключений со сбором статистики по времени (общее, медианное, 95 и 99 процентиль)