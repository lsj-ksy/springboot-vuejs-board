---
Springboot-Vue.js-Board
===
***

## ๐ Link
- **Deploy Site  :** http://team1.space/springboot-vuejs-board/ &ensp; <img src="https://img.shields.io/website?url=http%3A%2F%2Fteam1.space%2Fspringboot-vuejs-board%2F" />


- **Github Repository :** https://github.com/lsj-ksy/springboot-vuejs-board


## ๐ Summary
> ```Introduce Project```
>  - [๐ Features](#-features)
>  - [๐  Stack](#-stack)
>  - [๐ฟ Installation](#-installation)
>  - [๐ธ Site Screenshot](#-site-screenshot)
>  - [๐ง๐ปโ๐ป Team Member](#-team-member)
#
* * *
#
# Introduce Project
Spring Boot ์ Vue.js๋ฅผ ์ฌ์ฉํ์ฌ ๋ง๋  ๊ธฐ๋ณธํ ๊ฒ์ํ 2์ธ ํ๋ก์ ํธ ์๋๋ค.

๊ฒ์ํ ์นดํ๊ณ ๋ฆฌ ๊ตฌ๋ถ๊ณผ ์นดํ๊ณ ๋ฆฌ๋ณ ๊ฒ์๊ธ ์ ๋ ฌ, ํ์ด์ง, ํ์ผ ์๋ก๋ ๋ฐ ๋ฏธ๋ฆฌ๋ณด๊ธฐ, ๋๊ธ ๋ฑ Basicํ CRUD๋ฅผ ๊ตฌํํ์์ต๋๋ค.
<br><br/>
## ๐ Features
- Vue.js๋ฅผ ํ์ฉํ SPA ๋ฐฉ์์ ์ฌ์ดํธ ๊ตฌํ
- Bootstrap 5.0.2 ๋ฅผ ํ์ฉํ ์ฌ์ดํธ ๋์์ธ ๋ฐ modal ๊ธฐ๋ฅ ๊ตฌํ
- JPA ๋ฅผ ํ์ฉํ DB ๊ฐ๋ฐ
- Restful api ๊ตฌํ
- npm ์ ํ์ฉํ ํ์ด์ง์ฒ๋ฆฌ
- Gradle ๋น๋ ๋๊ตฌ ์ฌ์ฉ
- ๊ฒ์๊ธ, ๋๊ธ์ ๊ด๋ จํ ๊ธฐ๋ณธ์ ์ธ CRUD ๊ตฌํ
- ๊ฒ์๊ธ ํ์ผ ๋ฏธ๋ฆฌ๋ณด๊ธฐ, ์๋ก๋, ๋ค์ด๋ก๋ ๊ตฌํ
  <br><br/>
## ๐  Stack
### โ๏ธ Front-End
- Vue.js 3 ^3.0.0
- Vue-Router 4 (For Vue.js 3) ^4.0.0-0
- Vuex 4 (For Vue.js 3) ^4.0.2
- axios ^0.21.1
- Bootstrap ^5.0.2
- vue-sweetalert2 ^5.0.2
- v-pagination-3 ^0.1.6
- ckeditor5-vue ^2.0.1

### โ๏ธ Back-End
- Java v1.8
- Gradle v7.1.1
- Spring Boot v2.5.3
- Spring Boot Starter Data JPA v2.5.3
- Spring Boot Starter Validation v2.5.3
- Apache Commons IO v2.11.0
- MariaDB Java Client v2.7.3

### โ๏ธ DataBase
- MariaDB v10.5.10
  <br><br/>
## ๐ฟ Installation
```
# clone our repo
git clone https://github.com/lsj-ksy/springboot-vuejs-board.git
```

### `application.properties` File settings
>> src/main/resources/```application.proterties```
>   ~~~
> spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
> spring.datasource.url=<YOUR_DB_URL>
> spring.datasource.username=<YOUR_DB_USERNAME>
> spring.datasource.password=<YOUR_DB_PASSWORD>
> 
> spring.jpa.hibernate.ddl-auto=update
> logging.level.org.hibernate.sql=DEBUG
> spring.jpa.show-sql=true
> spring.jpa.properties.hibernate.format_sql=true
> 
> spring.servlet.multipart.max-file-size=10MB
> spring.servlet.multipart.max-request-size=20MB
>   ~~~

```
# change directory to our repo
cd springboot-vuejs-board

# start project
./gradlew bootRun

# build
./gradlew bootJar
java -jar SpringVue-0.0.1-SNAPSHOT.jar
```
<br><br/>
## ๐ธ Site Screenshot
![main](http://team1.space/upload/springboot-vuejs-board/screenshot/01-main.png)
![post-write](http://team1.space/upload/springboot-vuejs-board/screenshot/02-post_write.png)
![post-detail](http://team1.space/upload/springboot-vuejs-board/screenshot/03-post_detail.png)

## ๐ง๐ปโ๐ป Team Member
- ๊น์์ฐ : https://github.com/hi-sooyeon
- ์ด์น์ค : https://github.com/Zagg2732

<br><br/>
