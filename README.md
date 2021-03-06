[![codecov](https://codecov.io/gh/alex-skorikov/job4j_url_shortcut/branch/master/graph/badge.svg)](https://codecov.io/gh/alex-skorikov/job4j_url_shortcut)
[![Build Status](https://travis-ci.org/alex-skorikov/job4j_url_shortcut.svg?branch=master)](https://travis-ci.org/alex-skorikov/job4j_url_shortcut)



**Сервис - UrlShortCut**

Сервис работает через REST API. 

Требуемый функционал.

**1. Регистрация сайта.** 

Сервисом могут пользоваться разные сайты. Каждому сайту выдается пароль и логин.

Чтобы зарегистрировать сайт в систему нужно отправить запрос.

URL

POST /registration
C телом JSON объекта.

{site : "job4j.ru"}
Ответ от сервера.

{registration : true/false, login: УНИКАЛЬНЫЙ_КОД, password : УНИКАЛЬНЫЙ_КОД}
Плаг registration указывают, что регистрация выполнена или нет, то есть сайт уже есть в системе.

**1.1. Авторизация.**

Во всех остальных вызовах сервис должен проверять авторизацию пользователя.

Авторизацию сделать через JWT. Пользователь отправляет POST запрос с login и password и получает ключ.

Этот ключ отправляет в запросе в блоке HEAD.

Authorization: Bearer e25d31c5-db66-4cf2-85d4-8faa8c544ad6
 

**2. Регистрация URL.**

Поcле того, как пользователь зарегистрировал свой сайт он может отправлять на сайт ссылки и получать 
преобразованные ссылки.

Пример. 

Отправляем URL.

https://job4j.ru/TrackStudio/task/8993?thisframe=true
Получаем.

ZRUfdD2
Ключ ZRUfD2 ассоциирован с URL.

Опишем вызовы.

POST /convert
C телом JSON объекта.

{url: "https://job4j.ru/TrackStudio/task/8993?thisframe=true"}
Ответ от сервера.

{code: УНИКАЛЬНЫЙ_КОД}

**2.1 Переадресация. Выполняется без авторизации.** 

Когда сайт отправляет ссылку с кодом в ответ нужно вернуть ассоциированный адрес и статус 302.

Опишем вызовы.

GET /redirect/УНИКАЛЬНЫЙ_КОД
Ответ от сервера в заголовке.

HTTP CODE - 302 REDIRECT URL

**3. Статистика.**

В сервисе считается количество вызовов каждого адреса.

По сайту можно получить статистку всех адресов и количество вызовов этого адреса.

Опишем вызовы.

GET /statistic
Ответ от сервера JSON.

{
    {url : URL, total : 0},
    {url : "https://job4j.ru/TrackStudio/task/23", total : 103}
}
 

**Задание.**

1. Создайте репозиторий на github. Назовите его job4j_url_shortcut.

2. Реализуйте функционал описанный в задании. 

3. Для реализации используйте Spring Boot 2.

4. Загрузите код в githu. Оставьте ссылку на проект.
